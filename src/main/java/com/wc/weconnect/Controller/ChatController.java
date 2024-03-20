package com.wc.weconnect.Controller;

import com.wc.weconnect.exceptions.ChatException;
import com.wc.weconnect.exceptions.UserException;
import com.wc.weconnect.models.Chat;
import com.wc.weconnect.models.User;
import com.wc.weconnect.request.CreateChatRequest;
import com.wc.weconnect.service.ChatService;
import com.wc.weconnect.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChatController {
	
	@Autowired
	private ChatService chatService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/chats")
	public Chat createChat(@RequestHeader("Authorization") String jwt,
			@RequestBody CreateChatRequest req) throws ChatException, UserException {
		User reqUser = userService.findUserByJwt(jwt);
		User user2 = userService.findUserById(req.getUserId());
		Chat chat = chatService.createChat(reqUser, user2);
		
		return chat;		
	}
	
	@GetMapping("/api/chats")
	public List<Chat> findUsersChat(@RequestHeader("Authorization") String jwt) {
		
		User user = userService.findUserByJwt(jwt);
		List<Chat> chats = chatService.findUsersChat(user.getId());
		
		return chats;		
	}
	
	
}
