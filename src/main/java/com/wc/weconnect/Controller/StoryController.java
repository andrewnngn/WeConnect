package com.wc.weconnect.Controller;

import com.wc.weconnect.models.Story;
import com.wc.weconnect.models.User;
import com.wc.weconnect.service.StoryService;
import com.wc.weconnect.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StoryController {
	
	@Autowired
	private StoryService storyService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/story")
	public Story createStory(@RequestBody Story story, @RequestHeader("Authorization")String jwt) {
		
		User reqUser = userService.findUserByJwt(jwt);
		//create story
		Story createdStory = storyService.createStory(story, reqUser);
		
		return createdStory;	
	}
	
	@GetMapping("/api/story/user/{userId}")
	public List<Story> findUserStory(@PathVariable Integer userId ) throws Exception {
		
		
		//create story
		List<Story> stories = storyService.findStoryByUserId(userId);
		
		return stories;	
	}
	
}
