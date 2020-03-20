package com.usa.nj.gov.uhip.co.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usa.nj.gov.uhip.co.entity.CoPdfEntity;

@Repository("coPdfRepository")
public interface CoPdfRepository extends JpaRepository<CoPdfEntity, Serializable> {

}
