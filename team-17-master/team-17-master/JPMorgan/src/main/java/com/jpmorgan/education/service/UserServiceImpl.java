package com.jpmorgan.education.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpmorgan.education.entity.Opportunity;
import com.jpmorgan.education.entity.User;
import com.jpmorgan.education.repo.OppRepo;
import com.jpmorgan.education.repo.UserRepository;



@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private OppRepo oppRepo;
	
public void register(User user) {
		
		userRepo.save(user);

	}

@Override
public String changePassword(String userName, User user1) {
	
	User user2 = userRepo.findByUsername(userName);
		
		user2.setPassword(user1.getPassword());
	
		userRepo.save(user2);
		
		return "New password updated";

}

public String forgot(String userName, String question,String answer) throws Exception {
	
	User customer = userRepo.findByUsername(userName);

	
	
	if(customer==null) 
		throw new Exception("User name do not found");
	else if (customer.getSecretQuestion()==null || customer.getSecretAnswer()== null) 
		throw new Exception("Question & Answer were not set");
		
	else if (customer.getSecretQuestion().equals(question) && customer.getSecretAnswer().equals(answer))
			
			return "Details validated";

	else

		throw new Exception("Sorry your secret details are not matching");

}

@Override
public List<Opportunity> getAllOpportunitiesbyId(String Id) {

	User user = userRepo.findByuid(Id);
	return user.getOpportunities();
}

@Override
public List<Opportunity> getOpportunities(){
	
	List<Opportunity> Opportunities = new ArrayList<>();
	
	Opportunities = oppRepo.findAll();
	
	return Opportunities;

	
}

@Override
public String deleteOpp(String userId, int OppId) {
	
	User user = userRepo.findByuid(userId);

    int SIZE = user.getOpportunities().size();
	
	for (int i =0; i<SIZE; i++) {
	
		if(user.getOpportunities().get(i).getID()==OppId) {
			
			user.getOpportunities().remove(i);
			oppRepo.deleteById(OppId);
	
			return "Done";
		}
		}
	return "Error, something happened";

}

@Override
public Opportunity addOpportunity(String Id, Opportunity opportunity) throws Exception {
	
	User user = userRepo.findByuid(Id);
	Opportunity opportunity2= new Opportunity();
	
	opportunity2.setID(opportunity.getID());
	opportunity2.setSubject(opportunity.getSubject());
	opportunity2.setSchoolName(opportunity.getSchoolName());
	opportunity2.setCity(opportunity.getCity());
	opportunity2.setCountry(opportunity.getCountry());
	opportunity2.setHours(opportunity.getHours());
	opportunity2.setDescription(opportunity.getDescription());

		  user.getOpportunities().add(opportunity2);
		    
		 userRepo.save(user);
		   
		 return opportunity2;
	}
		
@Override
public String addOpportunities(String Id, List<Opportunity> opportunities) throws Exception {
	
	
	int SIZE = opportunities.size();
    
    if (SIZE == 0) {
    	throw new Exception("There is no opportunities to add");
    }
    else {
    	try {
	for (int i =0; i<SIZE; i++) {
	
		addOpportunity(Id,opportunities.get(i));
		
	}
	return "opportunities added";
    	}
    	catch (Exception e) {
    		 return "something Happened";
    	}
    		
    	}
    }
	
@Override
public Opportunity create(Opportunity opportunity) {
	
	return oppRepo.save(opportunity);
}
		
	
}
	

