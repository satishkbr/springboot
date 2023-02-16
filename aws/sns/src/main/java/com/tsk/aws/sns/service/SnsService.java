package com.tsk.aws.sns.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;
import software.amazon.awssdk.services.sns.model.SnsException;

@Service
//@RequiredArgsConstructor
public class SnsService {
	
	private static final Logger logger = LoggerFactory.getLogger(SnsService.class);
	
	@Value("${aws.topicArn}")
	private String topicArn;

    @Autowired
	private   SnsClient snsClient;
	
	//@Autowired
	/*
	 * public SnsService(SnsClient snsClient) { this.snsClient=snsClient; }
	 */
	


	public  void publishMessageToTopic(String message) {

		try {
			PublishRequest request = PublishRequest.builder()
					.message(message)
					.topicArn(topicArn)
					.build();

			PublishResponse result = snsClient.publish(request);
			logger.info(result.messageId() + " Message sent. Status is " + result.sdkHttpResponse().statusCode());

		} catch (SnsException e) {
			logger.info(e.awsErrorDetails().errorMessage());
		}
	}

}
