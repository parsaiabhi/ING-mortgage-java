package com.mortgage.ing.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mortgage.ing.entity.Mortgage;
import com.mortgage.ing.repository.MortgageRepository;

@Service
public class MortgageServiceImpl implements MortgageService {

	@Autowired
	private MortgageRepository mortagageRepository; 
	
	@Override
	public Optional<Mortgage> getMortgageDetailsByCustomerId(Integer customerId) {
		return mortagageRepository.findByCustomerId(customerId);
	}

	

}
