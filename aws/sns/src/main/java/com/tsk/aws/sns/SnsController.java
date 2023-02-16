package com.tsk.aws.sns;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tsk.aws.sns.service.SnsService;

@RestController
public class SnsController {
	
	@Autowired
	private  SnsService snsService;



	/*@GetMapping(path="/sns/publish/{message}")
	public void publishMessageToSNS(@PathVariable String message) {*/
	@PostMapping(path="/sns/publish")
	public void publishMessageToSNS(@RequestBody String message) {
		snsService.publishMessageToTopic(message);

	}
}
