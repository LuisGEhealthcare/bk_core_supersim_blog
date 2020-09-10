package br.com.supersim.blog.utils;

import java.io.IOException;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.ResponseHeaderOverrides;
import com.amazonaws.services.s3.model.S3Object;


public class AmazonUtils {
	
	private static final String GET_AWS_KEY_ID = "AKIAINGDN3ULCKHT7WWQ";
	private static final String GET_AWS_SECRET_KEY = "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";
	private static final String BUCKET_S3_NAME = "photos-supersim";
	private static final Regions CLIENT_REGION =  Regions.SA_EAST_1;
	
	public static void Upload(MultipartFile multipartFile, String fileName) throws IOException {
		
		ObjectMetadata data = new ObjectMetadata();
		data.setContentType(multipartFile.getContentType());
		data.setContentLength(multipartFile.getSize());
		
		try {
			BasicAWSCredentials awsCreds = new BasicAWSCredentials(GET_AWS_KEY_ID, GET_AWS_SECRET_KEY);
			AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
										.withRegion(CLIENT_REGION)
										.withCredentials(new AWSStaticCredentialsProvider(awsCreds))
										.build();
			
			PutObjectRequest request = new PutObjectRequest(BUCKET_S3_NAME, 
															fileName, 
															multipartFile.getInputStream(), data);
			
			ObjectMetadata metadata = new ObjectMetadata();
			
			request.setMetadata(metadata);
			
			s3Client.putObject(request);
			
		}catch (AmazonServiceException e) {
            e.printStackTrace();
        } catch (SdkClientException e) {
            e.printStackTrace();
        }
	}
	
	public static ResponseEntity<ByteArrayResource> Download(String fileName) throws IOException {
		
		S3Object headerOverrideObject = null;
		
		try{
			
			BasicAWSCredentials awsCreds = new BasicAWSCredentials(GET_AWS_KEY_ID, GET_AWS_SECRET_KEY);
			AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
										.withRegion(CLIENT_REGION)
										.withCredentials(new AWSStaticCredentialsProvider(awsCreds))
										.build();
			
            ResponseHeaderOverrides headerOverrides = new ResponseHeaderOverrides()
                    .withCacheControl("No-cache")
                    .withContentDisposition("attachment;" + fileName);
            
            GetObjectRequest getObjectRequestHeaderOverride = new GetObjectRequest(BUCKET_S3_NAME, fileName)
                    .withResponseHeaders(headerOverrides);
            
            headerOverrideObject = s3Client.getObject(getObjectRequestHeaderOverride);
            
            return FileUtils.downloadFile(headerOverrideObject.getObjectContent());
            
		}catch (AmazonServiceException e) {
            e.printStackTrace();
        }catch (SdkClientException e) {
            e.printStackTrace();
        }
		return null;
	}

}
