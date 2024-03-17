package com.wc.weconnect.service;

import com.WeConnect.models.Message;
import com.WeConnect.models.User;

import java.util.List;

public interface MessageService {
	
	public Message createMessage(User user, Integer chatId, Message req) throws Exception;
	public List<Message> findChatsMessages(Integer chatId) throws Exception;
}
