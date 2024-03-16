package com.wc.weconnect.service;

import com.WeConnect.models.Reels;
import com.WeConnect.models.User;

import java.util.List;


public interface ReelsService {
	
	public Reels createReels(Reels reel, User user);
	
	public List<Reels> findAllReels();
	
	public List<Reels> findUserReels(Integer userId) throws Exception;
	
	
}
