package com.jpmorgan.education.service;

import java.util.List;

import com.jpmorgan.education.entity.Opportunity;
import com.jpmorgan.education.entity.User;

public interface UserService {

	public void register(User user);
	public String changePassword(String userName, User user1);
	public String forgot(String userName, String question,String answer) throws Exception; 
	public List<Opportunity> getAllOpportunitiesbyId(String Id);
	public List<Opportunity> getOpportunities();
	public String deleteOpp(String userId, int OppId); 
	public Opportunity addOpportunity(String Id, Opportunity opportunity) throws Exception;
	public String addOpportunities(String Id, List<Opportunity> opportunities) throws Exception;
	public Opportunity create(Opportunity opportunity);
}
