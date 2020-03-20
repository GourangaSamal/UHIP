/**
 * 
 */
package com.usa.nj.gov.uhip.co.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.usa.nj.gov.uhip.co.entity.CoTriggersEntity;

/**
 * @author admin
 *
 */
@Repository("coTrgRepository")
public interface CoTriggersRepository extends JpaRepository<CoTriggersEntity, Serializable> {

	@Query(name = "from CoTriggersEntity where triggerStatus=:trgSw")
	public List<CoTriggersEntity> findAllPendingTriggers(String trgSw);

	@Query(value = "update CoTriggersEntity set triggerStatus=:status where triggerId=:trgId")
	public void updateStatus(Integer trgId, String status);
}
