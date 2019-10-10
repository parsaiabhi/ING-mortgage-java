package com.mortgage.ing.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mortgage.ing.dto.TransactionResponseDto;
import com.mortgage.ing.entity.Transaction;
import com.mortgage.ing.exception.NoAccountFoundException;
import com.mortgage.ing.repository.TransactionRepository;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public List<TransactionResponseDto> getTransactionByAccountNo(Integer accountNo)throws NoAccountFoundException{
		

		List<Transaction> transactionList = transactionRepository.findTransactionByAccountNo(accountNo);
		
		
		if(transactionList.isEmpty()) {
			throw new NoAccountFoundException("no account with accounNo " +accountNo);
		}
		
		log.debug("getting data from repository {}", transactionList);
		List<TransactionResponseDto> responseTransactionDto = new ArrayList<>();
		for (Transaction t : transactionList) {
			TransactionResponseDto transactionResponseDto = new TransactionResponseDto();

			BeanUtils.copyProperties(t, transactionResponseDto);

			responseTransactionDto.add(transactionResponseDto);

		}
		log.debug("dto conversion done {}", responseTransactionDto);
		return responseTransactionDto;
	}

}
