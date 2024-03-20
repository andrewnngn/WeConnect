import com.wc.weconnect.Controller.ChatController;

import com.wc.weconnect.exceptions.ChatException;
import com.wc.weconnect.service.ChatService;
import com.wc.weconnect.service.UserService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ChatControllerTest {


    // ChatController can create a chat successfully with valid input
    @Test
    public void test_createChat_validInput() throws ChatException, UserException {
        // Arrange
        ChatService chatService = mock(ChatService.class);
        UserService userService = mock(UserService.class);
        ChatController chatController = new ChatController();
        chatController.setChatService(chatService);
        chatController.setUserService(userService);
    
        String jwt = "valid_jwt";
        CreateChatRequest req = new CreateChatRequest();
        req.setUserId(1);
    
        User reqUser = new User();
        reqUser.setId(1);
    
        User user2 = new User();
        user2.setId(2);
    
        Chat expectedChat = new Chat();
    
        when(userService.findUserByJwt(jwt)).thenReturn(reqUser);
        when(userService.findUserById(req.getUserId())).thenReturn(user2);
        when(chatService.createChat(reqUser, user2)).thenReturn(expectedChat);
    
        // Act
        Chat result = chatController.createChat(jwt, req);
    
        // Assert
        assertEquals(expectedChat, result);
    }

    // ChatController throws ChatException when creating a chat with invalid input
    @Test
    public void test_createChat_invalidInput() throws ChatException, UserException {
        // Arrange
        ChatService chatService = mock(ChatService.class);
        UserService userService = mock(UserService.class);
        ChatController chatController = new ChatController();
        chatController.setChatService(chatService);
        chatController.setUserService(userService);
    
        String jwt = "valid_jwt";
        CreateChatRequest req = new CreateChatRequest();
        req.setUserId(1);
    
        User reqUser = new User();
        reqUser.setId(1);
    
        User user2 = new User();
        user2.setId(2);
    
        String errorMessage = "Invalid input";
    
        when(userService.findUserByJwt(jwt)).thenReturn(reqUser);
        when(userService.findUserById(req.getUserId())).thenThrow(new UserException(errorMessage));
    
        // Act and Assert
        assertThrows(ChatException.class, () -> chatController.createChat(jwt, req));
    }

}