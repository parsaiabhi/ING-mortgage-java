package com.mortgage.ing.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="emi")
public class Emi {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="emi_id")
	private Integer emiId;
	@Column(name="rate_of_interest")
	private Float rateOfInterest;
	@Column(name="term")
	private Float term;
	@Column(name="emi_amount")
	private Double emiAmount;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="mortgage_id")
	private Mortgage mortgage;
	
	public Emi() {
		super();
	}

	public Integer getEmiId() {
		return emiId;
	}

	public void setEmiId(Integer emiId) {
		this.emiId = emiId;
	}

	public Float getRateOfInterest() {
		return rateOfInterest;
	}

	public void setRateOfInterest(Float rateOfInterest) {
		this.rateOfInterest = rateOfInterest;
	}

	public Float getTerm() {
		return term;
	}

	public void setTerm(Float term) {
		this.term = term;
	}

	public Double getEmiAmount() {
		return emiAmount;
	}

	public void setEmiAmount(Double emiAmount) {
		this.emiAmount = emiAmount;
	}

	public Mortgage getMortgage() {
		return mortgage;
	}

	public void setMortgage(Mortgage mortgage) {
		this.mortgage = mortgage;
	}

	public Emi(Integer emiId, Float rateOfInterest, Float term, Double emiAmount, Mortgage mortgage) {
		super();
		this.emiId = emiId;
		this.rateOfInterest = rateOfInterest;
		this.term = term;
		this.emiAmount = emiAmount;
		this.mortgage = mortgage;
	}

	@Override
	public String toString() {
		return "Emi [emiId=" + emiId + ", rateOfInterest=" + rateOfInterest + ", term=" + term + ", emiAmount="
				+ emiAmount + ", mortgage=" + mortgage + "]";
	}
	
}
