package com.mortgage.ing.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mortgage.ing.dto.EmiRequestDto;

@Service
public class EmiServiceImpl implements EmiService{
	
	private static final Logger lOGGER = LoggerFactory.getLogger(EmiServiceImpl.class);
	
	@Override
	public void calculateEmi(EmiRequestDto emiRequestDto) {
		Double loanAmount = emiRequestDto.getPropertyValue() - emiRequestDto.getDepositAmount();
		//EMI = P × r × (1 + r)n/((1 + r)n - 1)
		Float roi = emiRequestDto.getRateOfInterest();
		Float tenure = emiRequestDto.getTerm() * 12;
		Double emi = (loanAmount * roi * (Math.pow((1+roi), tenure))) / Math.pow((1+roi),tenure-1);
		lOGGER.info("Calculated Emi is: "+emi);
	}

}
