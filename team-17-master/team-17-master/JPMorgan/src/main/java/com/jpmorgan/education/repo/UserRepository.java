package com.jpmorgan.education.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jpmorgan.education.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	User findByUsername(String username);
	
	User findByuid(String Id);

	  Boolean existsByUsername(String username);

	  Boolean existsByFullName(String fullname);
}
