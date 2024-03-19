package com.wc.weconnect.Controller;

import com.WeConnect.exceptions.UserException;
import com.WeConnect.models.User;
import com.WeConnect.repository.UserRepository;
import com.WeConnect.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
	
	
	@GetMapping("/api/users")
	public List<User> getUsers() {
		
		List<User> users = userRepository.findAll();
		
		return users;
	}
	
	@GetMapping("/api/users/{userId}")
	public User getUserById(@PathVariable("userId")Integer id) throws UserException { // here we access userId as we mention in the path variable 
		
		User userById = userService.findUserById(id);
		return userById;
	}
	
	
	
	// PutMapping is used to update record in database
	
	@PutMapping("/api/users")
	public User updateUser(@RequestHeader("Authorization")String jwt,@RequestBody User user) throws UserException {
		// from token we find user
		User reqUser = userService.findUserByJwt(jwt);// logged in user
		
		User updatedUser = userService.updateUser(user, reqUser.getId());// user which is logged in they only update their profile 
		return updatedUser;
	}
	
	@PutMapping("/api/users/follow/{userId2}")
	public User followUserHandler( @RequestHeader("Authorization")String jwt,@PathVariable Integer userId2) throws UserException {
		User reqUser = userService.findUserByJwt(jwt);
		User user = userService.followUser(reqUser.getId(), userId2);
		return user;
	}
	
	@GetMapping("/api/users/search")
	public List<User> searchUser(@RequestParam("query") String query){
		List<User> users = userService.searchUser(query);
		return users;
	}
	
	@GetMapping("/api/users/profile")
	public User getUserFromToken(@RequestHeader("Authorization")String jwt) {
		
		User user = userService.findUserByJwt(jwt);
		
		// as i don't want to send password to frontend
		
		user.setPassword(null);
		
		return user;
	}
	
}
