package com.ssn.fedral.gov.usastate;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssn.fedral.gov.stateentity.UsaState;

@Repository
public interface UsastatesRepository extends JpaRepository<UsaState, Serializable> {

}
