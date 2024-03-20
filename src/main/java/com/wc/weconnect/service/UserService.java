// Building a RESTful User API
package com.wc.weconnect.service;

import com.wc.weconnect.exceptions.UserException;
import com.wc.weconnect.models.User;

import java.util.List;

public interface UserService {
	
	public User registerUser(User user);
	
	public User findUserById(Integer userId) throws UserException;
	
	public User findUserByEmail(String email);
	
	public User followUser(Integer userId1, Integer userId2) throws UserException;
	
	public User updateUser(User user,Integer userId) throws UserException;
	
	public List<User> searchUser(String query);
	
	public User findUserByJwt(String jwt);
	
}
// for all these we have to write service