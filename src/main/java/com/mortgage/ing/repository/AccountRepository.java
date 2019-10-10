package com.mortgage.ing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mortgage.ing.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

	Account findByCustomerId(int customerId);

}
