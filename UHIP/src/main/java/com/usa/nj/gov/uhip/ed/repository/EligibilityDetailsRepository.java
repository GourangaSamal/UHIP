package com.usa.nj.gov.uhip.ed.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.usa.nj.gov.uhip.ed.entity.EligibilityDetailEntity;

@Repository("eligRepository")
public interface EligibilityDetailsRepository extends JpaRepository<EligibilityDetailEntity, Serializable> {

	@Query(value = "from EligibilityDetailsEntity where caseNum=:caseNo")
	public EligibilityDetailEntity findByCaseNum(Long caseNo);
}
