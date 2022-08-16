package com.jpmorgan.education.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.*;

public class UpdateRequest {
	
	  @NotBlank (message = "fullname must not be blank")
	  @Length(min = 2, max = 30, message = "fullname must be between 2-30 characters. ")
	  @Pattern(regexp = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$", message = "fullname is invalid.")
	private String fullName;
    
    @NotBlank (message = "Please choose an option for question")
    private String secretQuestion;
    
    @NotBlank (message = "Secret Answer must not be blank")
    @Size (min = 3, message = "Answer must be at least three characters")
    private String secretAnswer;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getSecretQuestion() {
		return secretQuestion;
	}

	public void setSecretQuestion(String secretQuestion) {
		this.secretQuestion = secretQuestion;
	}

	public String getSecretAnswer() {
		return secretAnswer;
	}

	public void setSecretAnswer(String secretAnswer) {
		this.secretAnswer = secretAnswer;
	}

	public UpdateRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UpdateRequest(
			@NotBlank(message = "Please choose an option for question") String secretQuestion,
			@NotBlank(message = "Secret Answer must not be blank") @Size(min = 3, message = "Answer must be at least three characters") String secretAnswer) {
		super();
		
		this.secretQuestion = secretQuestion;
		this.secretAnswer = secretAnswer;
	}

    
    
}
