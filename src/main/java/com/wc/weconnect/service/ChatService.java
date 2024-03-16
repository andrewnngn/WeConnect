package com.wc.weconnect.service;

import com.WeConnect.exceptions.ChatException;
import com.WeConnect.models.Chat;
import com.WeConnect.models.User;

import java.util.List;

public interface ChatService {
	
	public Chat createChat(User reqUser, User user2);
	
	public Chat findChatById(Integer chatId) throws ChatException;
	
	public List<Chat> findUsersChat(Integer userId);
	
}
