package com.WeConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.WeConnect.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{
	
}
