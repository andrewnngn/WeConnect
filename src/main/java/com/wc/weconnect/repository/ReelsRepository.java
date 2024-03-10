package com.wc.weconnect.repository;

import com.WeConnect.models.Reels;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReelsRepository extends JpaRepository<Reels, Integer>{
	
	public List<Reels> findByUserId(Integer userId);
	
}
