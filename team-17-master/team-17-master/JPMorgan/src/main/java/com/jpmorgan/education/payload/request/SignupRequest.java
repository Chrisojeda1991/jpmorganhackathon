package com.jpmorgan.education.payload.request;


import javax.validation.constraints.*;

import org.hibernate.validator.constraints.Length;


public class SignupRequest {
	
	@Email (message = "username must be in email form")
	@NotBlank (message = "username must not be blank")
	@Size(min = 2,max = 30,message = "username must be between 2-30 characters.")
	private String username;

  @NotBlank (message = "fullname must not be blank")
  @Length(min = 2, max = 30, message = "fullname must be between 2-30 characters. ")
  @Pattern(regexp = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$", message = "fullname is invalid.")
 // @Size(max = 50)
  private String fullName;


  @NotBlank (message = "password must not be blank")
  @Size(min = 6, max = 40,message = "password must be between 6-40 characters")
  private String password;
  
  @NotBlank (message = "This field must not be blank")
  private String password2;

  public String getPassword2() {
	return password2;
}

public void setPassword2(String password2) {
	this.password2 = password2;
}

public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getFullName() {
	return fullName;
}

public void setFullName(String fullName) {
	this.fullName = fullName;
}


public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
