package com.tsk.aws.sqsproducer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.sqs.model.Message;

@RestController
public class SqsController {
    private final SqsService sqsService;

    @Autowired
    public SqsController(SqsService sqsService) {
        this.sqsService = sqsService;
    }

    @PostMapping("/send")
    public void sendMessage(@RequestParam String message) {
        sqsService.sendMessage("https://sqs.ap-south-1.amazonaws.com/487460589850/DemoQueue", message);
    }

    @GetMapping("/receive")
    public List<Message> receiveMessages() {
        return sqsService.receiveMessages("https://sqs.ap-south-1.amazonaws.com/487460589850/DemoQueue");
    }
}
