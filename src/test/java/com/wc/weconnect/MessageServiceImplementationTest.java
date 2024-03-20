import com.wc.weconnect.models.Chat;
import com.wc.weconnect.service.MessageServiceImplementation;

import org.junit.Test;
import static org.junit.Assert.*;

public class MessageServiceImplementationTest {


    // Creating a message with valid user, chatId, and message content saves the message to the database and returns the saved message.
    @Test
    public void test_createMessage_validUserChatIdContent() throws Exception {
        // Arrange
        User user = new User();
        user.setId(1);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password");
        user.setGender("Male");
    
        Integer chatId = 1;
    
        Message req = new Message();
        req.setContent("Hello, world!");
    
        Chat chat = new Chat();
        chat.setId(chatId);
    
        Message savedMessage = new Message();
        savedMessage.setId(1);
        savedMessage.setContent("Hello, world!");
    
        when(chatService.findChatById(chatId)).thenReturn(chat);
        when(messageRepository.save(any(Message.class))).thenReturn(savedMessage);
    
        // Act
        Message result = messageService.createMessage(user, chatId, req);
    
        // Assert
        assertEquals(savedMessage, result);
        verify(chatService, times(1)).findChatById(chatId);
        verify(messageRepository, times(1)).save(any(Message.class));
    }

    // Creating a message with an invalid user throws an exception.
    @Test
    public void test_createMessage_invalidUser() throws Exception {
        // Arrange
        User user = null;
        Integer chatId = 1;
    
        Message req = new Message();
        req.setContent("Hello, world!");
    
        // Act and Assert
        assertThrows(Exception.class, () -> {
            messageService.createMessage(user, chatId, req);
        });
    }

}