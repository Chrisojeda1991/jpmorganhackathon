package com.jpmorgan.education.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jpmorgan.education.entity.Opportunity;

@Repository
public interface OppRepo extends JpaRepository<Opportunity, Integer>{

	
	
}
