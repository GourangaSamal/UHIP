package com.usa.nj.gov.uhip.co.batches;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.usa.nj.gov.uhip.co.model.CoBatchRunDetailsModel;
import com.usa.nj.gov.uhip.co.model.CoTriggersModel;
import com.usa.nj.gov.uhip.co.service.CoBatchService;
import com.usa.nj.gov.uhip.ed.model.EligibilityDetailModel;

public class CoPlanStatusSenderBatch {

	private static final String BATCH_NAME = "CO_PLAN_STATUS_SENDER";

	private static Integer SUCC_TRIGGERS = 0;
	private static Integer FAIL_TRIGGERS = 0;

	@Autowired
	private CoBatchService coBatchService;
	
		/**
		 * 1) preProcess() method will insert a record into batch_run_dtls table with 
		    batch_name ,start_date , batch_status
		 */
	public void preProcess() {
		// Insert batch start time with status as ST in
		// (BATCH_RUN_DETAILS)
		CoBatchRunDetailsModel model = new CoBatchRunDetailsModel();
		model.setBatchName(BATCH_NAME);
		model.setBatchRunStatus("ST");
		model.setStartDate(new Date());

		coBatchService.insertBatchRunDetails(model);
	}
	
	/**
	 *2) start( ) method will read all pending triggers from co_triggers table based on trg_Status  = 'P'.
	 */
	public void start() {
		List<CoTriggersModel> triggers = coBatchService.findAllPendingTriggers();
		if (!triggers.isEmpty()) {
			// -------------MT start -----------
			for (CoTriggersModel trigger : triggers) {
				process(trigger);
			}
			// -------------MT End----------------
		}
	}

	/**
	 * 3)process( ) method will be called for each pending trigger.
		3.1) process ( ) method will generate pdf with eligibility details for each trigger. 
		3.2) Generated pdf will be inserted into co_pdfs table
		3.3) Generated pdf will be sent in email as an attachment
		4) Trigger will be marked as completed (update trg_Status as 'C').
	 * @param trigger
	 */
	public void process(CoTriggersModel trigger) {
		try {
			// from trigger get case number
			long caseNo = trigger.getCaseNum();

			// using case num read eligibility data --- 1
			EligibilityDetailModel edModel = coBatchService.findByCaseNum(caseNo);

			// from elig data identify elig status
			String planStatus = edModel.getPlanStatus();

			// Based Status -- generate pdf
			if (planStatus.equals("AP")) {
				buildApPdf();
			} else if (planStatus.equals("DN")) {
				buildDnPdf();
			} else {
				buildTnPdf();
			}
			coBatchService.storePdf(null);

			// send pdf in email
			sendPdfEmail();

			// update trigger as completed ---- 3
			coBatchService.updateTrgStatus(trigger.getTriggerId(), "C");

			SUCC_TRIGGERS++;

		} catch (Exception e) {
			FAIL_TRIGGERS++;
		}
	}

	private void buildDnPdf() {
		// TODO Auto-generated method stub
	}

	private void buildApPdf() {
		// TODO Auto-generated method stub

	}

	private void buildTnPdf() {
		// TODO Auto-generated method stub

	}

	private void sendPdfEmail() {
		// TODO Auto-generated method stub
	}
	
	/**
	 * 5) postProcess( ) method will generate batch summary and will insert into BATCH_SUMMARY table.
       6) postProcess( ) method will update batch status as 'EN' in BATCH_RUN_DTLS table with end_date.
	 */
	public void postProcess() {
		// Generate Batch summary report and insert in DB (BATCH_SUMMARY)
		// update batch run status as EN in (BATCH_RUN_DETAILS)
	}

}
