package com.WeConnect.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.WeConnect.config.JwtProvider;
import com.WeConnect.models.User;
import com.WeConnect.repository.UserRepository;
import com.WeConnect.request.LoginRequest;
import com.WeConnect.response.AuthResponse;
import com.WeConnect.service.CustomUserDetailsService;
import com.WeConnect.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CustomUserDetailsService customUserDetails;
	
	
	// actual api localhost:8080/auth/signup     why auth because we use @RequestMapping("/auth")
	@PostMapping("/signup")
	public AuthResponse createUser(@RequestBody User user) throws Exception { // @RequestBody ->
		
		// Email which we get first we have to check that from that email user exist or not
		User isExist = userRepository.findByEmail(user.getEmail());
		
		if(isExist!=null) {
			throw new Exception("This email is already used with another account");
		}
		
		
		User newUser = new User();
		
		newUser.setEmail(user.getEmail());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setPassword(passwordEncoder.encode(user.getPassword()));
		
		User savedUser = userRepository.save(newUser);
		// after user is created we have to generate new token
		Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());
		//generate token 
		String token = JwtProvider.generateToken(authentication);
		// we have to pass this token in frontend and instead of this user we have to return token
		
		AuthResponse res = new AuthResponse(token,"Register success");
		return res;
	}
	
	
	//actual mapping /auth/singin
	@PostMapping("/signin")
	public AuthResponse signin(@RequestBody LoginRequest loginRequest) {
		
		Authentication authentication = authenticate(loginRequest.getEmail(),loginRequest.getPassword());
		String token = JwtProvider.generateToken(authentication);
		AuthResponse res = new AuthResponse(token,"Login success");
		return res;
	}

	private Authentication authenticate(String email, String password) {
		// here we check password the password we get from login request we match the password from the database
		
		UserDetails userDetails = customUserDetails.loadUserByUsername(email);
		
		if(userDetails == null) {
			throw new BadCredentialsException("Invalid username");
		}
		// if user loaded then we check the password String password and password from the userDetails is matching or not
		if(!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("password not match");		
		}
	
		return new UsernamePasswordAuthenticationToken(userDetails, 
														null, 
													userDetails.getAuthorities());
	}
	
}
