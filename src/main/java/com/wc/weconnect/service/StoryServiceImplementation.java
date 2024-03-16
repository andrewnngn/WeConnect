package com.wc.weconnect.service;

import com.WeConnect.models.Story;
import com.WeConnect.models.User;
import com.WeConnect.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StoryServiceImplementation implements StoryService{
	
	@Autowired
	private StoryRepository storyRepository;
	
	@Autowired
	private UserService userService;
	
	
	
	
	@Override
	public Story createStory(Story story, User user) {
		
		// lets create new story
		Story createdStory = new Story();
		
		createdStory.setCaption(story.getCaption());
		createdStory.setImage(story.getImage());
		createdStory.setUser(user);
		createdStory.setTimeStamp(LocalDateTime.now());
		
		// to save in database we need story repository
		return storyRepository.save(createdStory);
	}

	@Override
	public List<Story> findStoryByUserId(Integer userId) throws Exception {
		User user = userService.findUserById(userId);
		// find story by user id
		
		return storyRepository.findByUserId(userId);
	}
	
	
}
