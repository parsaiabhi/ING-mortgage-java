package com.mortgage.ing.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {

	@Id
	@Column(name = "account_no")
	private Long accountNo;
	@Column(name = "balance")
	private Double balance;
	@Column(name = "created_on")
	private LocalDate createdOn;
	@Column(name = "credit_score")
	private Integer creditScore;
	private Integer customerId;

}
