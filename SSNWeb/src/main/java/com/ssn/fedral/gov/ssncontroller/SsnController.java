package com.ssn.fedral.gov.ssncontroller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.ssn.fedral.gov.exception.CouldNotGenerateException;
import com.ssn.fedral.gov.property.SsnProperty;
import com.ssn.fedral.gov.ssnconstrant.SsnwebConstrant;
import com.ssn.fedral.gov.ssnentity.SsnMaster;
import com.ssn.fedral.gov.ssnmodel.SsnModel;
import com.ssn.fedral.gov.ssnservice.Ssnimpliment;

import oracle.net.aso.s;

@Controller
public class SsnController {
	@Autowired
	private Ssnimpliment ssnService;
	@Autowired
	private SsnProperty ssnProperty = new SsnProperty();
	SsnwebConstrant constant = new SsnwebConstrant();
	private static final Logger looger = LoggerFactory.getLogger(SsnController.class);

	/**
	 * Method showForm enrollSsn
	 *
	 * @param model
	 * @return Form
	 */
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String showForm(Model model) {
		looger.debug("**ShowForm Method Is  Executed**");
		SsnModel models = new SsnModel();
		model.addAttribute("ssnCmd", models);
		formValue(model);
		looger.debug("**Before Returning LogicalViewName**");
		looger.info("**Method Execution Complited**");
		return "Form";

	}

	/**
	 * Method ForformValue enrollSsn
	 * 
	 * @param model
	 * @return Form
	 */

	@RequestMapping(value = "/Register", method = RequestMethod.POST)
	public String enrollSsn(@ModelAttribute("ssnCmd") SsnModel models, Model model) {
		// local variable
		String id = null;
		Map<String, String> ssnProp = null;
		looger.info("**enrollSsn Method Execution Started**");
		// use service class
		SsnProperty ssaprops = new SsnProperty();
		looger.debug("**Before Calling ssnService.SsnEnrolment(models)**");
		
			Long ssn = ssnService.SsnEnrolment(models);
			// adding (-) in id
			id = new StringBuilder(ssn.toString()).insert(3, '-').insert(6, '-').toString();

			looger.trace("**ssn == 0**Before Condition");
			ssnProp = ssnProperty.getSsaprops();
			if (id == null) {
				looger.warn("**ssn == 0**", ssn);

				model.addAttribute(constant.FAILURE, ssnProp.get(constant.ENROL_FAILURE));
				looger.trace("Condition Execution Completed");

			} else {
				model.addAttribute(constant.SUCCESS, ssnProp.get(constant.ENROL_SUCCESS) + id);
			}

			formValue(model);
			looger.trace("**enrollSsn Execution Completed**");

		 
		return "Form";
	}

	/**
	 * Method For formValue SSNdetails
	 * 
	 * @param model
	 * @return
	 */

	public void formValue(Model model) {
		looger.debug("*formValue Method Execution Started*");

		List<String> genderlist = new ArrayList<String>();
		genderlist.add("MALE");
		genderlist.add("FEMALE");
		model.addAttribute("genders", genderlist);
		// Get all state From Service
		List<String> allState = ssnService.rettriveAllstate();
		model.addAttribute("allstate", allState);
		looger.info("**formValue Method Execution Complited**");
	}

	/**
	 * Method For Getting All Register SSNdetails
	 * 
	 * @param model
	 * @return Result
	 */
	@RequestMapping(value = "/getAll")
	public String getAllregistration(Model model) {
		// Use Service Class Method For Retrieving All Data
		List<SsnModel> allssn = ssnService.getAllssn();
		model.addAttribute("allssn", allssn);
		return "Result";
	}

}
