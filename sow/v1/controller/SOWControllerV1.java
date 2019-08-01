package com.accolite.ppm.sow.v1.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.accolite.app.security.SecureWithRoles;
import com.accolite.app.util.StatusResponse;
import com.accolite.ezhire.enums.ResponseStatus;
import com.accolite.ppm.sow.v1.model.SOWListMapModel;
import com.accolite.ppm.sow.v1.model.SOWMapModel;
import com.accolite.ppm.sow.v1.model.SOWv1;
import com.accolite.ppm.sow.v1.service.AWSService;
import com.accolite.ppm.sow.v1.service.SOWServiceV1;
import com.amazonaws.AmazonClientException;

@RestController
@RequestMapping(value = "/api/ppmSow/v1")
public class SOWControllerV1 {
	private String url;
	private static final Logger logger = LogManager.getLogger(SOWControllerV1.class);
	@Autowired
	SOWServiceV1 sowService;
	@Autowired
	AWSService s;

	@SecureWithRoles(roles = { "PROJECT_MANAGEMENT_OFFICE", "BU_HEAD", "OPERATIONS_HEAD" })
	@RequestMapping(value = "/addSow", method = RequestMethod.POST)
	public Object addSow(@RequestBody SOWMapModel newSow) {
		List<String> errors = new ArrayList<String>();
		SOWv1 sow = assignSow(newSow);
		try {
			logger.info("adding sow " + sow.getSowId() + " " + sow.getName());
			sow = sowService.saveSow(sow);
			return new StatusResponse(ResponseStatus.SUCCESS.getStatus(), "Sucessfully Inserted");

		} catch (ConstraintViolationException dupEx) {
			logger.error("Exception due to Aldready Existing SOW id" + dupEx.getMessage());
			errors.add("Already Existing SOW id.");
			errors.add(dupEx.toString());
			errors.add(dupEx.getMessage());

		} catch (Exception ex) {
			logger.error("Exception in add sow " + ex.getMessage());
			errors.add("Exception");
			errors.add(ex.toString());
			errors.add(ex.getMessage());
		}
		return new StatusResponse(ResponseStatus.ERROR.getStatus(), "Insertion Errors", errors);
	}

	private SOWv1 assignSow(SOWMapModel newSow) {
		SOWv1 sow = new SOWv1();
		sow = newSow.getSowDetails();
		sow.setTimeAndMaterial(newSow.getTimeAndMaterial());
		sow.setFixedBid(newSow.getFixedBid());
		sow.setFixedMonthlyBid(newSow.getFixedMonthlyBid());

		sow.setAttachments(newSow.getAttachments());
		sow.setBillingInfo(newSow.getBillingInfo());
		return sow;
	}

	@SecureWithRoles(roles = { "ALL" })
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String uploadObject(@RequestParam("file") MultipartFile file) throws AmazonClientException {
		List<String> errors = new ArrayList<String>();
		try {
			this.url = s.uploadObj("com.accolite", file);

		} catch (Exception ex) {

			logger.error("Exception in upload sow " + ex.getMessage());
			errors.add("Exception");
			errors.add(ex.toString());
			errors.add(ex.getMessage());
			return "Exception raised " + ex.getMessage();
		}
		return this.url;
	}

	@SecureWithRoles(roles = { "PROJECT_MANAGEMENT_OFFICE", "BU_HEAD", "OPERATIONS_HEAD" })
	@RequestMapping(value = "/updateSow", method = RequestMethod.POST)
	public Object updateSow(@RequestBody SOWMapModel newSow) {
		List<String> errors = new ArrayList<String>();
		System.out.println(newSow);
		SOWv1 sow = assignSow(newSow);
		try {
			logger.info("updating sow " + sow.getSowId() + " " + sow.getName());
			sow = sowService.updateSow(sow);
			return new StatusResponse(ResponseStatus.SUCCESS.getStatus(), "Sucessfully Updated");
		} catch (Exception ex) {
			logger.error("Exception in update sow " + ex.getMessage());
			errors.add("Exception");
			errors.add(ex.getMessage());
			errors.add(ex.toString());
		}
		return new StatusResponse(ResponseStatus.ERROR.getStatus(), "Updation Errors", errors);
	}

	@SecureWithRoles(roles = { "PROJECT_MANAGEMENT_OFFICE", "BU_HEAD", "OPERATIONS_HEAD" })
	@RequestMapping(value = "/getAllSowByRole", method = RequestMethod.GET)
	public List<SOWListMapModel> getAllSowByRole() {
		return sowService.getAllSowByRole();
	}

	@SecureWithRoles(roles = { "PROJECT_MANAGEMENT_OFFICE", "BU_HEAD", "OPERATIONS_HEAD" })
	@RequestMapping(value = "/listAllSow", method = RequestMethod.GET)
	public List<SOWv1> listAllSow() {
		return sowService.getAllSow();
	}

	@SecureWithRoles(roles = { "PROJECT_MANAGEMENT_OFFICE", "BU_HEAD", "OPERATIONS_HEAD" })
	@RequestMapping(value = "/getSowById", method = RequestMethod.GET)
	public Object GetSowById(@RequestParam String sowId) {
		List<String> errors = new ArrayList<String>();
		try {
			return sowService.getSowById(sowId);
		} catch (Exception ex) {
			logger.error("Exception in GET SOW BY ID" + ex.getMessage());
			errors.add("Exception");
			errors.add(ex.getMessage());
			errors.add(ex.toString());
		}
		return new StatusResponse(ResponseStatus.ERROR.getStatus(), "Insertion Error", errors);
	}

}
