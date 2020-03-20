package com.usa.nj.gov.uhip.co.service;

import java.util.List;

import com.usa.nj.gov.uhip.co.model.CoBatchRunDetailsModel;
import com.usa.nj.gov.uhip.co.model.CoPdfModel;
import com.usa.nj.gov.uhip.co.model.CoTriggersModel;
import com.usa.nj.gov.uhip.ed.model.EligibilityDetailModel;

public interface CoBatchService {

	public boolean insertBatchRunDetails(CoBatchRunDetailsModel model);

	public List<CoTriggersModel> findAllPendingTriggers();

	public EligibilityDetailModel findByCaseNum(Long caseNo);

	public boolean updateTrgStatus(Integer trgId, String status);

	public boolean storePdf(CoPdfModel model);
}
