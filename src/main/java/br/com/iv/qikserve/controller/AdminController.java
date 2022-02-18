package br.com.iv.qikserve.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

	@PostMapping(value = "/shutdown")
	private void shutdownApplication(){
		System.exit(1);
	}
	
}
