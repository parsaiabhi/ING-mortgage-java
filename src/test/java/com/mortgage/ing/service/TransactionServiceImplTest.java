/*package com.mortgage.ing.service;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mortgage.ing.dto.TransactionResponseDto;
import com.mortgage.ing.entity.Account;
import com.mortgage.ing.entity.Customer;
import com.mortgage.ing.entity.Mortgage;
import com.mortgage.ing.entity.Transaction;
import com.mortgage.ing.exception.NoAccountFoundException;
import com.mortgage.ing.repository.AccountRepository;
import com.mortgage.ing.repository.MortgageRepository;
import com.mortgage.ing.repository.TransactionRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class TransactionServiceImplTest {

	@Mock
	TransactionRepository transactionRepository;
	@Mock
	MortgageRepository mortgageRepository;
	@Mock
	AccountRepository accountRepository;
	
	@InjectMocks
	TransactionServiceImpl transactionServiceImpl;

	@Test
	public void testGetTransactionByAccountNo() throws NoAccountFoundException {
		Optional<Account> account = Optional.of(new Account());
		Account acc = account.get();
//		acc.getCustomer();
		Customer customer =  new Customer();
		customer.setCustomerId(12);
		Integer cid = customer.getCustomerId();
		
		Mortgage mortgage = new Mortgage();
		mortgage.setCustomerId(cid);
		mortgage.setDepositAmount(334d);
		mortgage.setMortgagaeId(12);
		mortgage.setMortgageAccountNo(2234l);
		mortgage.setMortgageLoanAmount(1000000d);
		mortgage.setMortgageStatus("approved");
		mortgage.setOutstandingBalance(10000d);
		mortgage.setPropertyName("vile");
		mortgage.setPropertyType("residencial");
		mortgage.setPropertyValue(3000000d);
		
		Optional<Mortgage> mort = Optional.of(new Mortgage());
		
		
		Mockito.when(accountRepository.findById(2298l)).thenReturn(account);
		
		Mockito.when(mortgageRepository.findByCustomerId(cid)).thenReturn(mort);
		
		
		
		
		
		Transaction transaction = new Transaction();
		transaction.setAccountNo(2298l);
		transaction.setDescription("emi for property loan ");
		transaction.setTransactiondate(LocalDate.now());
		transaction.setTransactionId(1);
		transaction.setTransactionType("debited");
		transaction.setTransactionAmount(100000d);

		List<Transaction> transactionList = new ArrayList<>();
		transactionList.add(transaction);

		List<TransactionResponseDto> transactionByAccountNo = transactionServiceImpl.getTransactionByAccountNo(2298l);
		
		Mockito.when(transactionRepository.findTransactionByAccountNo(2298l)).thenReturn(transactionList);


		List<TransactionResponseDto> responseTransactionDto = new ArrayList<>();
		for (Transaction t : transactionList) {
			TransactionResponseDto transactionResponseDto = new TransactionResponseDto();

			BeanUtils.copyProperties(t, transactionResponseDto);

			responseTransactionDto.add(transactionResponseDto);

		}

		assertEquals(responseTransactionDto.get(0).getAccountNo(), transactionByAccountNo.get(0).getAccountNo());

	}

}
*/