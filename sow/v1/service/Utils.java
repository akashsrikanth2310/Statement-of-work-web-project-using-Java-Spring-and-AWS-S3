package com.accolite.ppm.sow.v1.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Utils {
	@Value("${ezhire.aws.accessKey}")
	private String awsAccessKey;
	
	@Value("${ezhire.aws.secretKey}")
	private String awsSecretKey;
	
	public String getAwsAccessKey() {
		return awsAccessKey;
	}

	public void setAwsAccessKey(String awsAccessKey) {
		this.awsAccessKey = awsAccessKey;
	}

	public String getAwsSecretKey() {
		System.out.println(awsSecretKey);
		return awsSecretKey;
	}

	public void setAwsSecretKey(String awsSecretKey) {
		this.awsSecretKey = awsSecretKey;
	}

	

}
