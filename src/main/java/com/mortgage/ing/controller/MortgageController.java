package com.mortgage.ing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.mortgage.ing.dto.ResponseDto;
import com.mortgage.ing.service.CustomerService;
import com.mortgage.ing.service.MortgageService;
import com.mortgage.ing.util.IngMortgageConstants;

@RestController
public class MortgageController {

	@Autowired
	CustomerService customerService;
	@Autowired
	MortgageService mortgageService;

	// check if customer is valid to apply mortgage
	@GetMapping("/customer/{customerId}/mortgages")
	public ResponseEntity<ResponseDto> customerMortgageValidation(@PathVariable("customerId") int customerId) {
		String message = customerService.customerMortgageValidation(customerId);
		ResponseDto responseDto = new ResponseDto();
		if (message.equalsIgnoreCase(IngMortgageConstants.INVALID)) {
			responseDto.setMessage(IngMortgageConstants.CUSTOMER_NOT_APPLICABLE_FOR_MORTGAGE);
			responseDto.setStatusCode(400);
			return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
		} else {
			responseDto.setMessage(IngMortgageConstants.CUSTOMER_APPLICABLE_FOR_MORTGAGE);
			responseDto.setStatusCode(200);
			return new ResponseEntity<>(responseDto, HttpStatus.OK);
		}
	}	
	
	

}