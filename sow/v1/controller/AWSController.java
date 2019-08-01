package com.accolite.ppm.sow.v1.controller;


import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.apache.commons.validator.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.accolite.app.security.SecureWithRoles;
import com.accolite.ppm.sow.v1.model.AWS;
import com.accolite.ppm.sow.v1.service.AWSService;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;

@RestController
@RequestMapping("/aws")
public class AWSController {
	@Autowired
	AWSService s;
	@SecureWithRoles(roles = {"ALL"})
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createBucket(@RequestBody String bucket) {
		return s.createBucket(bucket);
	}
	
	@SecureWithRoles(roles = {"ALL"})
	@RequestMapping(value = "/uploadAws", method = RequestMethod.POST)
	public String uploadObject(@RequestParam("bucket") String bucket, @RequestParam("file") MultipartFile file)
			throws AmazonServiceException, AmazonClientException, NoSuchAlgorithmException {
		System.out.println("sdsds");
		String url = s.uploadObj(bucket, file);
	/*	UrlValidator urlValidator = new UrlValidator();
		if (urlValidator.isValid(url)) {
			return "Uploaded Succesfully";
		} else {
			return "Could not Upload";
		}*/
		
		return url;

	}
	@SecureWithRoles(roles = {"ALL"})
	@RequestMapping(value = "/download", method = RequestMethod.POST)
	public String downloadObject(@RequestBody String url) throws IOException, AmazonServiceException, AmazonClientException, URISyntaxException {
		return s.downloadContent(url);
	}

	@RequestMapping(value = "/deleteBucket", method = RequestMethod.POST)
	public String deleteBucket(@RequestBody String bucket) {
		return s.deleteBucketImpl(bucket);
	}

	@RequestMapping(value = "/deleteObject", method = RequestMethod.POST)
	public String deleteObject(@RequestBody AWS aws) {
		return s.deleteObjectImpl(aws);
	}

	@RequestMapping(value = "/listObjects", method = RequestMethod.POST)
	public List<String> listObjects(@RequestBody String bucket) {
		return s.listObjects(bucket);
	}

	@RequestMapping(value = "/listBuckets")
	public List<String> listBuckets() {
		return s.listBuckets();
	}
}

