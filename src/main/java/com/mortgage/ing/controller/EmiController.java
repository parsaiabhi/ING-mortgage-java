package com.mortgage.ing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mortgage.ing.dto.EmiRequestDto;
import com.mortgage.ing.dto.EmiResponseDto;
import com.mortgage.ing.service.EmiService;

@RestController
public class EmiController {
	
	@Autowired
	EmiService emiService;
	
	@GetMapping("/mortgages")
	public ResponseEntity<EmiResponseDto> calculateEmi(@RequestParam("depositAmount") Double depositAmount,@RequestParam("propertyValue") Double propertyValue,
			@RequestParam("rateOfInterest") Float rateOfInterest, @RequestParam("term") Float term){
		EmiRequestDto emiRequestDto = new EmiRequestDto();
		emiRequestDto.setDepositAmount(depositAmount);
		emiRequestDto.setPropertyValue(propertyValue);
		emiRequestDto.setRateOfInterest(rateOfInterest);
		emiRequestDto.setTerm(term);
		emiService.calculateEmi(emiRequestDto);
		
		return null;
	}

}
