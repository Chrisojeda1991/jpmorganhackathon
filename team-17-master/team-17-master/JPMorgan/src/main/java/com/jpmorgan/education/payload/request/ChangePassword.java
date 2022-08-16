package com.jpmorgan.education.payload.request;


import javax.validation.constraints.*;


public class ChangePassword {

	@NotBlank (message = "password must not be blank")
	 @Size(min = 6, max = 40,message = "password must be between 6-40 characters")
  private String password;
	
	@NotBlank (message = "This field must not be blank")
 private String password2;

public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

public String getPassword2() {
	return password2;
}

public void setPassword2(String password2) {
	this.password2 = password2;
}

}
