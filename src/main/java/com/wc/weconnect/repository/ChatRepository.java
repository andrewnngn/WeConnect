package com.WeConnect.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.WeConnect.models.Chat;
import com.WeConnect.models.User;

public interface ChatRepository extends JpaRepository<Chat, Integer>{
	
	public List<Chat> findByUsersId(Integer userId);
	
	// we have to create method which contains both users
	@Query("select c from Chat c where :user Member of c.users And :reqUser Member of c.users")
	public Chat findChatByUsersId(@Param("user") User user, @Param("reqUser") User reqUser);
	
}
