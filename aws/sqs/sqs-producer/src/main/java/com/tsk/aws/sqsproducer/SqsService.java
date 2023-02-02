package com.tsk.aws.sqsproducer;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;

@Service
public class SqsService {
    private final AmazonSQS sqs;

    public SqsService(@Value("${aws.accessKeyId}") String accessKey,
                      @Value("${aws.secretKey}") String secretKey) {
        sqs = AmazonSQSClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(
                        new BasicAWSCredentials(accessKey, secretKey)))
                .build();
    }

    public void sendMessage(String queueUrl, String message) {
        sqs.sendMessage(queueUrl, message);
    }

    public List<Message> receiveMessages(String queueUrl) {
        ReceiveMessageRequest request = new ReceiveMessageRequest(queueUrl);
        return sqs.receiveMessage(request).getMessages();
    }
}

