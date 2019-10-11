package com.mortgage.ing.service;

import java.util.Optional;

import com.mortgage.ing.entity.Mortgage;

public interface MortgageService {
	
	Optional<Mortgage> getMortgageDetailsByCustomerId(Integer customerId); 

}
