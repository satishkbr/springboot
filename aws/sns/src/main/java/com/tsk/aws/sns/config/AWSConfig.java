package com.tsk.aws.sns.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.services.sns.SnsClient;

@Configuration
public class AWSConfig {
	@Value("${aws.accessKeyId}")
	private String accessKeyId;
	
	@Value("${aws.secretKey}")
	private String secretKey;
	
	@Bean
	public AwsBasicCredentials awsCreds()
	{
		return AwsBasicCredentials.create(
				accessKeyId,
				secretKey);
	}
	//return several clients as beans
	@Bean
	public SnsClient snsClient(final AwsBasicCredentials awsCreds) {
		return SnsClient.builder().credentialsProvider(StaticCredentialsProvider.create(awsCreds))
	    .build();
	}
    

}
