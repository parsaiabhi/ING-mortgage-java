package com.mortgage.ing.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mortgage.ing.entity.Customer;
import com.mortgage.ing.repository.CustomerRepository;
import com.mortgage.ing.util.IngMortgageConstants;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Override
	public String customerMortgageValidation(int customerId) {
		Optional<Customer> customer = customerRepository.findById(customerId);
		LocalDate start = customer.get().getDateOfBirth();
		LocalDate end = LocalDate.now();
		long years = ChronoUnit.YEARS.between(start, end);
		String message = years>=18 && customer.get().getAccount().getCreditScore()>=18 &&
				customer.get().getAccount().getCreditScore()<=700 && customer.get().getOccupationType().equalsIgnoreCase("salaried")
				?IngMortgageConstants.VALID:IngMortgageConstants.INVALID;
		return message;
	}

}
