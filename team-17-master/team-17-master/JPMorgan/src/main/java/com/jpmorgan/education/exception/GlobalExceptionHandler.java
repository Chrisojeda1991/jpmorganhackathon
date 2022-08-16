package com.jpmorgan.education.exception;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {

    Map<String, List<String>> body = new HashMap<>();

    List<String> errors = ex.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(DefaultMessageSourceResolvable::getDefaultMessage)
        .collect(Collectors.toList());

    List<String> UN = new ArrayList<String>();
    List<String> FN = new ArrayList<String>();
    List<String> PW = new ArrayList<String>();
    List<String> PW2 = new ArrayList<String>();
    List<String> SQ = new ArrayList<String>();
    List<String> SA = new ArrayList<String>();
   
 
    for (int i=0;i<errors.size();i++) {
    if(errors.get(i).contains("username"))
    	UN.add(errors.get(i));
    else if(errors.get(i).contains("fullname"))
    	FN.add(errors.get(i));
    else if(errors.get(i).contains("password"))
    	PW.add(errors.get(i));
    else if(errors.get(i).contains("field"))
    	PW2.add(errors.get(i));
    else if(errors.get(i).contains("question"))
    	SQ.add(errors.get(i));
    else if(errors.get(i).contains("Answer"))
    	SA.add(errors.get(i));
   
    body.put("username", UN);
    body.put("fullname", FN);
    body.put("password", PW);
    body.put("password2", PW2);
    body.put("secretQuestion", SQ);
    body.put("secretAnswer", SA);
    }
    return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
  }
}