package com.ssn.fedral.gov.ssnRepository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssn.fedral.gov.ssnentity.SsnMaster;

@Repository
public interface SsnRepositorry extends JpaRepository<SsnMaster, Serializable> {

}
