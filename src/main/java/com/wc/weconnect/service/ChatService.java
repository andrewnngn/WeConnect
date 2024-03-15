package com.WeConnect.service;

import java.util.List;

import com.WeConnect.exceptions.ChatException;
import com.WeConnect.models.Chat;
import com.WeConnect.models.User;

public interface ChatService {
	
	public Chat createChat(User reqUser, User user2);
	
	public Chat findChatById(Integer chatId) throws ChatException;
	
	public List<Chat> findUsersChat(Integer userId);
	
}
