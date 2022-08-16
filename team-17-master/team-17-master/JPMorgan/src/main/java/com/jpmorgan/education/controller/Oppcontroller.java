package com.jpmorgan.education.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpmorgan.education.entity.Opportunity;
import com.jpmorgan.education.payload.response.MessageResponse;
import com.jpmorgan.education.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class Oppcontroller {
	
	@Autowired
	UserService oppService;
	
	 @PostMapping("/Opportunity/create")
	  public ResponseEntity<?> createOpp(@Valid @RequestBody Opportunity opportunity) {
		  
		 try {
			Opportunity opportunity2 = oppService.create(opportunity);
		  
			if (opportunity2!=null ) {
				return ResponseEntity.ok(new MessageResponse("opportunity succesfully added"));	
			}else
				return ResponseEntity.badRequest().body(new MessageResponse("Sorry Opportunity not added"));
			}
			catch(Exception e) {
		
			return ResponseEntity.badRequest().body(new MessageResponse("Sorry Opportunity not added2"));
			}
			
	  }

}
