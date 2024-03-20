package com.wc.weconnect.service;

import com.wc.weconnect.exceptions.ChatException;
import com.wc.weconnect.models.Chat;
import com.wc.weconnect.models.User;

import java.util.List;

public interface ChatService {
	
	public Chat createChat(User reqUser, User user2);
	
	public Chat findChatById(Integer chatId) throws ChatException;
	
	public List<Chat> findUsersChat(Integer userId);
	
}
