package com.jpmorgan.education.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
public class UserContoller {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/{UserName}/forgot/{Question}/{Answer}")
	public ResponseEntity<Object> forgotPassword(@PathVariable("UserName") String userName,
			@PathVariable("Question") String question,@PathVariable("Answer") String answer)
	{
		
		try {
			
			return ResponseEntity.ok(userService.forgot(userName,question,answer));
			}
			catch (Exception e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
	}

	@GetMapping("/{Id}/opportunity")
	public ResponseEntity<Object> getAllOpportunitiesByUserId(@PathVariable("Id") String Id) {
		
		try {
		List<Opportunity> opportunities = userService.getAllOpportunitiesbyId(Id);
		return ResponseEntity.ok(opportunities);
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponse("Please check ID"));
		}
	}
	
	@GetMapping("/opportunity")
	public ResponseEntity<Object> getAllOpportunities(){
		try {
		List<Opportunity> opportunities = userService.getOpportunities();
		return ResponseEntity.ok(opportunities);
		}
		catch(Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponse("There is nothing available"));
		}
	}
	

		@DeleteMapping("/{userId}/Opportunity/{OppId}")
		public ResponseEntity<?> deleteoppById(@PathVariable("userId") String userId,@PathVariable("OppId") int OppId) {
			try {
			String msg = userService.deleteOpp(userId,OppId);
			return ResponseEntity.ok(new MessageResponse(msg));
			}
			catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponse("Check Input"));

			}
		} 
	
		@PostMapping("/{userId}/opportunity")
		public ResponseEntity<?> addOpportunity(@PathVariable ("userId") String userId, @RequestBody Opportunity opportunity) {
				try {
				
				Opportunity opportunity2 = userService.addOpportunity(userId,opportunity);
				if (opportunity2!=null ) {
					return ResponseEntity.ok(new MessageResponse("opportunity sussesfully added"));	
				}else
					return ResponseEntity.badRequest().body(new MessageResponse("Sorry Opportunity not added"));
				}
				catch(Exception e) {
			
				return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
		
				}
		}
		
		@PostMapping("/{userId}/opportunities")
		public ResponseEntity<?> addOpportunities(@PathVariable ("userId") String userId, @RequestBody List<Opportunity> opportunities) {
				try {
				
					String msg = userService.addOpportunities(userId,opportunities);
				
					return ResponseEntity.ok(new MessageResponse(msg));	
				}
				catch(Exception e) {
			
				return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
		
				}
		}
		
		
		
		
		
}
