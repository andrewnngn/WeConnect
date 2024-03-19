package com.WeConnect.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
public class HomeController {
	
	@GetMapping
	public String homeControllerHandler() {
		return "this is home controller";
	}
	
	// now i want to change end point
	
	@GetMapping("/home")
	public String homeControllerHandler2() {
		return "this is home controller2";
	}
	
	//spring doc end point
	@GetMapping("/leetcode")
	public String homeControllerHandler3() {
		return "leetcode problems";
	}

}
