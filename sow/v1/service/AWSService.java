package com.accolite.ppm.sow.v1.service;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.accolite.ppm.sow.v1.model.AWS;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3URI;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.BucketVersioningConfiguration;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.s3.model.SetBucketVersioningConfigurationRequest;
import com.google.gson.Gson;

@Service
public class AWSService {

	@Autowired
	private Utils util;

	Region us = Region.getRegion(Regions.US_WEST_2);

	private AWSCredentials credentials;
	private AmazonS3Client s3;

	public AmazonS3Client initializeS3Client() {

		if (credentials == null) {
			credentials = new BasicAWSCredentials(util.getAwsAccessKey(), util.getAwsSecretKey());
		}

		if (s3 == null) {
			s3 = new AmazonS3Client(credentials);
		}

		return s3;
	}

	public String createBucket(String bucket) throws AmazonServiceException, AmazonClientException {

		initializeS3Client();
		s3.setRegion(us);
		Gson gson = new Gson();
		String jsonbucket = "{ \"bucket\" :  \" " + bucket + "\" }";
		AWS cb = gson.fromJson(jsonbucket, AWS.class);
		String buck = cb.getBucket();
		s3.createBucket(buck);
		return bucket;

	}

	public String uploadObj(String bucket, MultipartFile file) throws IOException {

		initializeS3Client();

		String bucketName = bucket;
		StringBuilder sb = new StringBuilder(file.getOriginalFilename());
		int len = sb.length();
		String orgFilename = sb.substring(0, len - 4);

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		String keyName = "$" + orgFilename + "." + timestamp + ".pdf";

		if (!(s3.doesBucketExist(bucketName)))
			bucketName = createBucket(bucketName);

		BufferedOutputStream bout = null;
		try {
			byte byteArray[] = file.getBytes();

			bout = new BufferedOutputStream(new FileOutputStream(sb.toString()));
			bout.write(byteArray);
			bout.flush();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		finally {
			try {
				if (bout != null)
					bout.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}

		}

		File fileName = new File(sb.toString());

		PutObjectRequest por = new PutObjectRequest(bucketName, keyName, fileName);
		s3.putObject(por);

		s3.setObjectAcl(bucketName, keyName, CannedAccessControlList.PublicRead);

		StringBuilder url = new StringBuilder(((AmazonS3Client) s3).getResourceUrl(bucketName, keyName));
		String urlUnsecured = url.substring(0, 4).concat(url.substring(5));

		return urlUnsecured;

	}

	public String downloadContent(String url) throws URISyntaxException, IOException {

		initializeS3Client();
		URI fileToBeDownloaded = new URI(url);

		AmazonS3URI s3URI = new AmazonS3URI(fileToBeDownloaded);

		S3Object obj = s3.getObject(s3URI.getBucket(), s3URI.getKey());
		BufferedReader reader = new BufferedReader(new InputStreamReader(obj.getObjectContent()));
		String content = "";
		while (true) {
			String line = reader.readLine();
			if (line == null)
				break;
			content = content.concat(line);

		}
		return content;
	}

	public String deleteBucketImpl(String bucket) {
		s3.setRegion(us);
		Gson gson = new Gson();
		AWS cb = gson.fromJson(bucket, AWS.class);
		String buck = cb.getBucket();
		s3.deleteBucket(buck);
		return bucket;

	}

	public String deleteObjectImpl(AWS aws) {
		String bucketName = aws.getBucket();
		String key = aws.getKey();

		s3.deleteObject(bucketName, key);

		return bucketName + "/" + key;
	}

	public List<String> listObjects(String bucket) {
		List<String> list = new ArrayList<String>();
		s3.setRegion(us);
		Gson gson = new Gson();
		AWS lo = gson.fromJson(bucket, AWS.class);
		String buck = lo.getBucket();
		ObjectListing objectListing = s3.listObjects(new ListObjectsRequest().withBucketName(buck));
		for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
			list.add(objectSummary.getKey() + " (size = " + objectSummary.getSize() + "B)");

		}
		return list;
	}

	public List<String> listBuckets() {
		List<String> list = new ArrayList<String>();
		for (Bucket bucket : s3.listBuckets()) {
			list.add(bucket.getName());

		}
		return list;
	}

	public String enableVersioning(String bucket) {
		BucketVersioningConfiguration configuration = new BucketVersioningConfiguration().withStatus("Enabled");

		SetBucketVersioningConfigurationRequest setBucketVersioningConfigurationRequest = new SetBucketVersioningConfigurationRequest(
				bucket, configuration);

		s3.setBucketVersioningConfiguration(setBucketVersioningConfigurationRequest);
		BucketVersioningConfiguration conf = s3.getBucketVersioningConfiguration(bucket);
		return conf.getStatus();
	}

}
