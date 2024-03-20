package com.wc.weconnect.service;

import com.wc.weconnect.models.Story;
import com.wc.weconnect.models.User;

import java.util.List;

public interface StoryService {
	
	public Story createStory(Story story, User user);
	
	public List<Story> findStoryByUserId(Integer userId) throws Exception;
	
	
}
