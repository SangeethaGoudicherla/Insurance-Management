package com.insurance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insurance.entity.InsuranceCompany;

public interface InsuranceCompanyRepository extends JpaRepository<InsuranceCompany, Long>{

}
