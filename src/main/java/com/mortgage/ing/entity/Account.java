package com.mortgage.ing.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="account")
public class Account {
	
	@Id
	@Column(name="account_no")
	private Long accountNo;
	@Column(name="balance")
	private Double balance;
	@Column(name="created_on")
	private LocalDate createdOn;
	@Column(name="credit_score")
	private Integer creditScore;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	public Account() {
		super();
	}

	public Long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(Long accountNo) {
		this.accountNo = accountNo;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public LocalDate getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDate createdOn) {
		this.createdOn = createdOn;
	}

	public Integer getCreditScore() {
		return creditScore;
	}

	public void setCreditScore(Integer creditScore) {
		this.creditScore = creditScore;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Account [accountNo=" + accountNo + ", balance=" + balance + ", createdOn=" + createdOn
				+ ", creditScore=" + creditScore + ", customer=" + customer + "]";
	}

	public Account(Long accountNo, Double balance, LocalDate createdOn, Integer creditScore, Customer customer) {
		super();
		this.accountNo = accountNo;
		this.balance = balance;
		this.createdOn = createdOn;
		this.creditScore = creditScore;
		this.customer = customer;
	}
	
	
	
}
