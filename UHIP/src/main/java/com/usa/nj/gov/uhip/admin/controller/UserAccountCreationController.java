package com.usa.nj.gov.uhip.admin.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.usa.nj.gov.uhip.admin.constants.AppConstants;
import com.usa.nj.gov.uhip.admin.model.UserAccount;
import com.usa.nj.gov.uhip.admin.properties.AppProperties;
import com.usa.nj.gov.uhip.admin.service.UserAccountService;
import com.usa.nj.gov.uhip.admin.service.UserAccountServiceImpl;

@Controller
public class UserAccountCreationController {
	@Autowired
	private UserAccountService userAccService;
	@Autowired
	private AppProperties properties;
	private static final Logger logger=LoggerFactory.getLogger(UserAccountCreationController.class);
	/**
	 * This method is used load user Account creation form
	 * @param model
	 * @return
	 */
	@RequestMapping(value= { "/createUserAcc"},method=RequestMethod.GET)
	public String loadAccCreationForm(Model model) {
		UserAccount acc=new UserAccount();
		//set the value to model object by using constant class(not hard coded)
		model.addAttribute("USER_ACC_MODEL",acc);
		intializeFormValues(model);
		//return logical view name by using constants class
		return AppConstants.USER_ACC_CREATION_VIEW;
		
		
	}
/**
 * This method used for avoiding double posting problem with remove model parameter in method to take redirectAttribute.
 * bcoz one controller method communicate with another controller method by redirect:/accRegSuccess
 * @param userAcc
 * @param redirectAttrs
 * @return
 */
	@RequestMapping(value="/createUserAccount",method=RequestMethod.POST)
	public String createUserAccount(@ModelAttribute("userAccModel")UserAccount userAcc,RedirectAttributes redirectAttrs) {
		
		logger.info("form Data::"+userAcc);
		boolean isSaved=userAccService.createUserAccount(userAcc);
		Map<String,String>props= properties.getUhipprops();
		if(isSaved) {
			//sucess msg
			String msg=	props.get("accCreationSucess");
			redirectAttrs.addFlashAttribute("succMsg",msg);
		}
		else {
			//failure msg
			String msg=props.get("accCreationFailure");
			redirectAttrs.addAttribute("failMsg",msg);
		}
		intializeFormValues(redirectAttrs);
		//return logical view name
		/*return AppConstants.USER_ACC_CREATION_VIEW;*/
		
		//goto this  accRegSuccess() not returning view name.
		return "redirect:/accRegSuccess";
		
	}
	public void intializeFormValues(Model model) {
		List<String> genderList =new ArrayList();
		genderList.add("Male");
		genderList.add("Fe-Male");
		model.addAttribute(AppConstants.GENDERS_LIST, genderList);
		
		List<String> rolesList= new ArrayList();
		rolesList.add("Case worker");
		rolesList.add("Admin");
		model.addAttribute(AppConstants.ROLES_LIST,rolesList);
		
	}
	@RequestMapping(value="/checkEmail",method=RequestMethod.GET)
	public @ResponseBody String checkEmail(HttpServletRequest req) {
		logger.debug("** Email validation Started **");
		System.out.println("CheckEmail()");
		String email=req.getParameter("email");
		if(email==null || "" .equals(email)) {
			logger.error("** Email Is Missing in Request **");
		}
		String response=userAccService.validateEmail(email);
		logger.debug("** Email validation Ended **");
		logger.info("** Email Validation Complted Successfully **");
		//here response is not logical view name
		return response;
	}
	
	/**
	 * Tis method is used to display success msg .
	 * also avoiding double posting problem/duplicate form submission by help of
	 *  post redirect get design pattern
	 * @return
	 */
	@RequestMapping(value="/accRegSuccess",method=RequestMethod.GET)
	public String accRegSuccess(Model model) {
		logger.info("** accRegSuccess() called ** ");
		UserAccount acc=new UserAccount();
		model.addAttribute("USER_ACC_MODEL", acc);
		intializeFormValues(model);
		
		return AppConstants.USER_ACC_CREATION_VIEW;
		
	}
	
	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/getAllAccounts", method=RequestMethod.GET)
	//@GetMapping(value="/getAllAccounts")
	public String getAllAccounts(Model model) {
	List<UserAccount> listModel=userAccService.retrieveAllAccounts();
		if(!listModel.isEmpty()) {
			model.addAttribute(AppConstants.LIST_MODEL, listModel);
		}
		else {
			model.addAttribute(AppConstants.NO_DATA, AppConstants.NO_DATA_IN_TABLE);
		}
		return "accounts";
		
	}
	@RequestMapping(value="/updateActiveSwitch",method=RequestMethod.GET)
	//@GetMapping(value="/updateActiveSwitch")
	public String updateActiveSwitch( @PathVariable("userId")Integer userId,RedirectAttributes redirect) {
		String  flag="updated";
		String status=userAccService.updateActiveSwitch(userId);
		redirect.addFlashAttribute("status", status);
		return "redirect:/getAllAccounts";
		
	}
	@GetMapping(value= {"/loadUpdateForm/{userId}"})
	public String loadUpdateUserForm(@PathVariable("userId") Integer userId,Model model) {
		
	    UserAccount userAccountModel =userAccService.getAccById(userId);
	    model.addAttribute("userAccountModel", userAccountModel);
	    getFormDetails( model);
		return "update";
		
	}
	private void getFormDetails(Model model)
	{
		logger.debug("");
		//creating the gender list
		ArrayList<String> gender=new ArrayList<String>(2);
		gender.add("Male");
		gender.add("Female");

		//setting the gender to form
		model.addAttribute("gender",gender);

		//creating the caseworker list
		ArrayList<String> role=new ArrayList<String>(2);
		role.add("Admin");
		role.add("CaseWorker");

		//setting the role to form
		model.addAttribute("role",role);
		logger.debug("");
		logger.debug("");
	}
	
	

}
