package com.jpmorgan.education.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpmorgan.education.entity.User;
import com.jpmorgan.education.jwt.JwtUtils;
import com.jpmorgan.education.payload.request.ChangePassword;
import com.jpmorgan.education.payload.request.LoginRequest;
import com.jpmorgan.education.payload.request.SignupRequest;
import com.jpmorgan.education.payload.response.JwtResponse;
import com.jpmorgan.education.payload.response.MessageResponse;
import com.jpmorgan.education.repo.UserRepository;
import com.jpmorgan.education.service.UserDetailsImpl;
import com.jpmorgan.education.service.UserService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class AuthController {

	@Autowired
	  AuthenticationManager authenticationManager;
	
	@Autowired
	  UserRepository userRepository;
	
	@Autowired
	  UserService userService;
	
	@Autowired
	  PasswordEncoder encoder;
	
	  @Autowired
	  JwtUtils jwtUtils;
	
	@PostMapping("/user/authenticate")
	  public ResponseEntity<?> authenticateCustomer(@Valid @RequestBody LoginRequest loginRequest) {

		  try {
	    Authentication authentication = authenticationManager.authenticate(
	        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

	    SecurityContextHolder.getContext().setAuthentication(authentication);
	    String jwt = jwtUtils.generateJwtToken(authentication);
	    
	    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal(); 
	    
	 
	  
	    return ResponseEntity.ok(new JwtResponse(jwt, 
	                         userDetails.getId(), 
	                         userDetails.getUsername()
	                        ));
		  }
	    catch(Exception e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new MessageResponse("Not valid"));

	    }
	  }
	
	 //Register User
	  @PostMapping("/user/register")
	  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		  
	    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
	      return ResponseEntity
	    		  .status(HttpStatus.FORBIDDEN)
	          .body(new MessageResponse("Username is already taken!"));
	    }
	    else if (!signUpRequest.getPassword().equals(signUpRequest.getPassword2() )) {
	    	return ResponseEntity
	      		  .status(HttpStatus.FORBIDDEN)
	            .body(new MessageResponse("Passwords are not matching"));
	    }

	    User user = new User(signUpRequest.getUsername(), 
	               signUpRequest.getFullName(),
	               encoder.encode(signUpRequest.getPassword()));

			userService.register(user);
			
			//return ResponseEntity.accepted().body(new MessageResponse("User registered successfully!"));
			return ResponseEntity.status(HttpStatus.CREATED).build();
		  
	  }
	  
	  @PostMapping("user/{UserName}/forgot")
	  public ResponseEntity<?> changePassword(@PathVariable ("UserName") String UserName,@Valid @RequestBody ChangePassword changePassword) {

		  if (!changePassword.getPassword().equals(changePassword.getPassword2() )) {
		    	return ResponseEntity
		      		  .status(HttpStatus.FORBIDDEN)
		            .body(new MessageResponse("Passwords are not matching"));
		    }
		  
		  
	   User user= new User(encoder.encode(changePassword.getPassword()));

	   try {
			
			return ResponseEntity.ok(new MessageResponse(userService.changePassword(UserName,user)));
		}

			catch(Exception e) {
				return ResponseEntity.badRequest().body(new MessageResponse("Sorry password not updated"));
			}

	  }
	
}
