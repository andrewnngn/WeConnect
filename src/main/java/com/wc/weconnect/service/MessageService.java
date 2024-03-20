package com.wc.weconnect.service;

import com.wc.weconnect.models.Message;
import com.wc.weconnect.models.User;

import java.util.List;

public interface MessageService {
	
	public Message createMessage(User user, Integer chatId, Message req) throws Exception;
	public List<Message> findChatsMessages(Integer chatId) throws Exception;
}
