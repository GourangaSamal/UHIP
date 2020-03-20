package com.usa.nj.gov.uhip.co.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usa.nj.gov.uhip.co.entity.CoBatchRunDetailsEntity;
import com.usa.nj.gov.uhip.co.entity.CoPdfEntity;
import com.usa.nj.gov.uhip.co.entity.CoTriggersEntity;
import com.usa.nj.gov.uhip.co.model.CoBatchRunDetailsModel;
import com.usa.nj.gov.uhip.co.model.CoPdfModel;
import com.usa.nj.gov.uhip.co.model.CoTriggersModel;
import com.usa.nj.gov.uhip.co.repository.CoBatchRunDetailsRepository;
import com.usa.nj.gov.uhip.co.repository.CoPdfRepository;
import com.usa.nj.gov.uhip.co.repository.CoTriggersRepository;
import com.usa.nj.gov.uhip.ed.entity.EligibilityDetailEntity;
import com.usa.nj.gov.uhip.ed.model.EligibilityDetailModel;
import com.usa.nj.gov.uhip.ed.repository.EligibilityDetailsRepository;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

@Service
public class CoBatchServiceImpl implements CoBatchService {

	@Autowired
	private CoBatchRunDetailsRepository batchRunRepo;

	@Autowired
	private CoTriggersRepository coTrgRepo;

	@Autowired
	private EligibilityDetailsRepository edDetailRepo;

	@Autowired
	private CoPdfRepository coPdfRepo;

	@Override
	public boolean insertBatchRunDetails(CoBatchRunDetailsModel model) {
		CoBatchRunDetailsEntity entity = new CoBatchRunDetailsEntity();
		BeanUtils.copyProperties(model, entity);
		entity = batchRunRepo.save(entity);
		return entity.getRunSeq() > 0;
	}

	@Override
	public List<CoTriggersModel> findAllPendingTriggers() {
		List<CoTriggersEntity> entities = coTrgRepo.findAllPendingTriggers("P");
		List<CoTriggersModel> models = new ArrayList();
		if (!entities.isEmpty()) {
			for (CoTriggersEntity entity : entities) {
				CoTriggersModel model = new CoTriggersModel();
				BeanUtils.copyProperties(entity, model);
				models.add(model);
			}
		}
		return models;
	}

	@Override
	public EligibilityDetailModel findByCaseNum(Long caseNo) {
		EligibilityDetailEntity entity = edDetailRepo.findByCaseNum(caseNo);
		EligibilityDetailModel model = new EligibilityDetailModel();
		BeanUtils.copyProperties(entity, model);
		return model;
	}

	@Override
	public boolean updateTrgStatus(Integer trgId, String status) {
		coTrgRepo.updateStatus(trgId, status);
		return true;
	}

	@Override
	public boolean storePdf(CoPdfModel model) {
		CoPdfEntity entity = new CoPdfEntity();
		BeanUtils.copyProperties(entity, model);
		entity = coPdfRepo.save(entity);
		return entity.getCoPdfId() > 0;
	}

}
