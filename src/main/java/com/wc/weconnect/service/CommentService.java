package com.WeConnect.service;

import com.WeConnect.models.Comment;

public interface CommentService {
	
	public Comment createComment(Comment comment, Integer postId, Integer userId) throws Exception;
	
	public Comment findCommentById(Integer commentId) throws Exception;
	
	public Comment likeComment(Integer Comment, Integer userId) throws Exception; 
		
	
}
