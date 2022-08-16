package com.jpmorgan.education.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Data
@NoArgsConstructor

@Entity
public class User {


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "user_seq")
    @GenericGenerator(name = "user_seq", strategy = "com.jpmorgan.education.util.UserIdGenerator")
	
    private String uid;
	
    @Column(name = "username")
    private String username;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "password")
    private String password;
    
    private double hours;
    
    private String supportReq;
    
    private String supportReqId;
    
    private String secretQuestion=null;
    private String secretAnswer=null;

    @OneToMany (cascade = CascadeType.ALL) 
    private List<Opportunity> opportunities;
	
    public User(String username, String fullName, String password) {
		super();
		this.username = username;
		this.fullName = fullName;
		this.password = password;
		
	}
	public User(String password) {
		super();
		this.password = password;
	}
    
}
