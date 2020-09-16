package com.claudemirojr.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.claudemirojr.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUserName(String userName);
	
	//@Query("SELECT u FROM User u WHERE u.userName =:userName")
	//User findByUserName(@Param("userName") String userName);	

}
