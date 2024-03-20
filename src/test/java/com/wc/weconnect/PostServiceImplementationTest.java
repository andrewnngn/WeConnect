import com.wc.weconnect.models.Post;
import com.wc.weconnect.repository.PostRepository;
import com.wc.weconnect.repository.UserRepository;
import com.wc.weconnect.service.PostServiceImplementation;

import com.wc.weconnect.service.UserService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PostServiceImplementationTest {


    // createNewPost method creates a new post with valid data and returns it
    @Test
    public void test_createNewPost_validData() throws Exception {
        // Arrange
        PostServiceImplementation postService = new PostServiceImplementation();
        PostRepository postRepositoryMock = mock(PostRepository.class);
        UserService userServiceMock = mock(UserService.class);
        UserRepository userRepositoryMock = mock(UserRepository.class);
        postService.setPostRepository(postRepositoryMock);
        postService.setUserService(userServiceMock);
        postService.setUserRepository(userRepositoryMock);
    
        Post post = new Post();
        post.setCaption("Test Caption");
        post.setImage("Test Image");
        post.setVideo("Test Video");
    
        User user = new User();
        user.setId(1);
    
        when(userServiceMock.findUserById(1)).thenReturn(user);
        when(postRepositoryMock.save(any(Post.class))).thenReturn(post);
    
        // Act
        Post result = postService.createNewPost(post, 1);
    
        // Assert
        assertEquals(post.getCaption(), result.getCaption());
        assertEquals(post.getImage(), result.getImage());
        assertEquals(post.getVideo(), result.getVideo());
    }

    // createNewPost method throws an exception for invalid userId
    @Test
    public void test_createNewPost_invalidUserId() throws Exception {
        // Arrange
        PostServiceImplementation postService = new PostServiceImplementation();
        UserService userServiceMock = mock(UserService.class);
        postService.setUserService(userServiceMock);
    
        when(userServiceMock.findUserById(1)).thenThrow(new UserException("User not found"));
    
        // Act and Assert
        assertThrows(Exception.class, () -> {
            postService.createNewPost(new Post(), 1);
        });
    }

}