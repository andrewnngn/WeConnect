import com.wc.weconnect.service.CommentServiceImplementation;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CommentServiceImplementationTest {


    // createComment method successfully creates a comment and adds it to the post's comments list
    @Test
    public void test_create_comment_successfully() throws Exception {
        // Arrange
        CommentServiceImplementation commentService = new CommentServiceImplementation();
        Comment comment = new Comment();
        comment.setContent("Test comment");
        Integer postId = 1;
        Integer userId = 1;
    
        // Act
        Comment createdComment = commentService.createComment(comment, postId, userId);
    
        // Assert
        assertNotNull(createdComment);
        assertEquals(comment.getContent(), createdComment.getContent());
        assertEquals(userId, createdComment.getUser().getId());
        assertTrue(createdComment.getCreatedAt().isBefore(LocalDateTime.now()));
    
        // Clean up (optional)
        // Delete the created comment from the post's comments list
    }

    // createComment method throws an exception if the provided user or post does not exist
    @Test
    public void test_create_comment_with_invalid_user_or_post() {
        // Arrange
        CommentServiceImplementation commentService = new CommentServiceImplementation();
        Comment comment = new Comment();
        comment.setContent("Test comment");
        Integer postId = 1;
        Integer userId = 999; // Invalid user ID
    
        // Act and Assert
        assertThrows(Exception.class, () -> {
            commentService.createComment(comment, postId, userId);
        });
    }

}