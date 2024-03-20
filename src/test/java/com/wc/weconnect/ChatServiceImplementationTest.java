package com.wc.weconnect;

import com.wc.weconnect.exceptions.ChatException;
import com.wc.weconnect.models.Chat;
import com.wc.weconnect.models.User;
import com.wc.weconnect.service.ChatServiceImplementation;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ChatServiceImplementationTest {


    // create a chat between two users and return the chat object
    @Test
    public void test_create_chat() {
        // Arrange
        User reqUser = new User();
        User user2 = new User();
    
        // Act
        ChatServiceImplementation chatService = new ChatServiceImplementation();
        Chat chat = chatService.createChat(reqUser, user2);
    
        // Assert
        assertNotNull(chat);
    }

    // throw a ChatException when finding a chat by id that does not exist
    @Test
    public void test_find_chat_by_id_chat_not_found() {
        // Arrange
        Integer chatId = 1;
    
        // Act
        ChatServiceImplementation chatService = new ChatServiceImplementation();
    
        // Assert
        assertThrows(ChatException.class, () -> {
            chatService.findChatById(chatId);
        });
    }

}