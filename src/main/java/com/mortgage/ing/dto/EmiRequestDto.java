package com.mortgage.ing.dto;

public class EmiRequestDto {
	
	private Double depositAmount;
	private Double propertyValue;
	private float rateOfInterest;
	private float term;
	
	public EmiRequestDto() {
		super();
	}

	public Double getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(Double depositAmount) {
		this.depositAmount = depositAmount;
	}

	public Double getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(Double propertyValue) {
		this.propertyValue = propertyValue;
	}

	public float getRateOfInterest() {
		return rateOfInterest;
	}

	public void setRateOfInterest(float rateOfInterest) {
		this.rateOfInterest = rateOfInterest;
	}

	public float getTerm() {
		return term;
	}

	public void setTerm(float term) {
		this.term = term;
	}
	
}
