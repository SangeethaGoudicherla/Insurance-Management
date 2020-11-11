package com.insurance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.insurance.entity.Insurance;


public interface InsuranceRepository extends JpaRepository<Insurance, Long>{

@Query("From Insurance ins where ins.insurance_company_id =:companyId")
public List<Insurance> getInsuranceByinsurance_company_id(@Param("companyId")Long companyId);
}
