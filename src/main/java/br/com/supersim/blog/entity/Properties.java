//package br.com.supersim.blog.entity;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@ConfigurationProperties
//public class Properties {
//	
//	private String bucketS3Name;
//	private String awsKeyId;
//	private String awsSecretKey;
//	
//	public Properties(
//			@Value("${br.com.supersim.bucket-s3-name}") String bucketS3Name, 
//			@Value("${br.com.supersim.aws-key-id}") String awsKeyId, 
//			@Value("${br.com.supersim.aws-secret-key}") String awsSecretKey) {
//		this.bucketS3Name = bucketS3Name;
//		this.awsKeyId = awsKeyId;
//		this.awsSecretKey = awsSecretKey;
//	}
//
//	public String getBucketS3Name() {
//		return bucketS3Name;
//	}
//
//	public void setBucketS3Name(String bucketS3Name) {
//		this.bucketS3Name = bucketS3Name;
//	}
//
//	public String getAwsKeyId() {
//		return awsKeyId;
//	}
//
//	public void setAwsKeyId(String awsKeyId) {
//		this.awsKeyId = awsKeyId;
//	}
//
//	public String getAwsSecretKey() {
//		return awsSecretKey;
//	}
//
//	public void setAwsSecretKey(String awsSecretKey) {
//		this.awsSecretKey = awsSecretKey;
//	}
//
//}
