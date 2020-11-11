package com.insurance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.insurance.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	public List<Customer> findByaadharNumber(@Param("aadharNumber") String aadharNumber);
}
