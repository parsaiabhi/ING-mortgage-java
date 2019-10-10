package com.mortgage.ing.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mortgage.ing.entity.Account;
import com.mortgage.ing.entity.Customer;
import com.mortgage.ing.entity.Emi;
import com.mortgage.ing.entity.Mortgage;
import com.mortgage.ing.entity.Transaction;
import com.mortgage.ing.exception.IngMortgageException;
import com.mortgage.ing.exception.InvalidCredentialsException;
import com.mortgage.ing.repository.AccountRepository;
import com.mortgage.ing.repository.CustomerRepository;
import com.mortgage.ing.repository.EmiRepository;
import com.mortgage.ing.repository.MortgageRepository;
import com.mortgage.ing.repository.TransactionSummaryRepository;
import com.mortgage.ing.util.IngMortgageConstants;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Laxman
 * @desc This service will take care of all transaction from transaction account
 *       to mortgage account
 *
 */
@Service
@Slf4j
public class SchedulerServiceImpl implements SchedulerService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	MailService mailService;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private TransactionSummaryRepository txnRepository;

	@Autowired
	private MortgageRepository mortgageRepository;

	@Autowired
	private EmiRepository emiRepository;

	/**
	 * This method will take
	 */
	@Override
	public void runTask() {

		log.info("SchedulerServiceImpl :: runTask - starts");

		List<Integer> customerIds = customerRepository.getDistinctCustomerId();
		customerIds.stream().forEach(customerId -> {

			log.info("SchedulerServiceImpl :: runTask - userId : ");

			Account account = accountRepository.findByCustomerId(customerId);
			log.info("inside account={}", account.getAccountNo());
			Mortgage mortgage = mortgageRepository.findByCustomerId(customerId);
			Emi emi = emiRepository.findByMortgageId(mortgage.getMortgagaeId());
			Optional<Customer> customer = customerRepository.findByCustomerId(customerId);

			if (account.getBalance() > emi.getEmiAmount()) {
				if (mortgage.getOutstandingBalance() >= 0) {
					Float tenure = emi.getTerm() * 12;

					mortgage.setOutstandingBalance(mortgage.getOutstandingBalance() - emi.getEmiAmount());
					mortgageRepository.save(mortgage);

					account.setBalance(account.getBalance() - emi.getEmiAmount());
					account.setBalance(account.getBalance() + IngMortgageConstants.MONTHLY_INCOME);
					accountRepository.save(account);

					Transaction mortgageTransaction = new Transaction();
					mortgageTransaction.setAccountNo(mortgage.getMortgageAccountNo());
					mortgageTransaction.setTransactionAmount(mortgage.getOutstandingBalance());
					mortgageTransaction.setDescription("Mortgage Account Credit!");
					mortgageTransaction.setTransactionDate(LocalDateTime.now());
					mortgageTransaction.setTransactionType("CREDITED");
					txnRepository.save(mortgageTransaction);

					Transaction accountTransaction = new Transaction();
					accountTransaction.setAccountNo(account.getAccountNo());
					accountTransaction.setTransactionAmount(account.getBalance());
					accountTransaction.setDescription("Savings Account Debit!");
					accountTransaction.setTransactionDate(LocalDateTime.now());
					accountTransaction.setTransactionType("DEBITED");

					txnRepository.save(accountTransaction);
					tenure--;
					if (tenure >= 3) {
						mailService.sendEmail(customer.get().getEmailId(),
								" this is your last" + tenure + " month emi");
					}
				}
			}
		});

		log.info("SchedulerServiceImpl :: runTask - END");
	}
}
