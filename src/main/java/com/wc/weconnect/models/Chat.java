package com.wc.weconnect.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Chat {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String chat_name;
	
	private String chat_image;
	
	@ManyToMany
	private List<User> users = new ArrayList<>();
	
	private LocalDateTime timeStamp;
	
	@OneToMany(mappedBy = "chat")// chat which is defined in Message we used mapped to avoid extra table
	private List<Message> messages = new ArrayList<>();
	
}
