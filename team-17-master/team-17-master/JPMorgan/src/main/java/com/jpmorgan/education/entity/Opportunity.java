package com.jpmorgan.education.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Data
@NoArgsConstructor
@Entity
public class Opportunity {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int ID;
	
	private String subject;
	
	private String schoolName;
	
	private String city;
	
	private String country;
	
	private double hours;
	
	private String  description;

}
