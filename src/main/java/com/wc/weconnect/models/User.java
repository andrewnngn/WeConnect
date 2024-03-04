package com.wc.weconnect.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String gender;
    @ManyToMany(mappedBy = "user")
    private List<User> followers = new ArrayList<>();

    @ManyToMany(mappedBy = "user")
    private List<User> followings = new ArrayList<>();

    @JsonIgnore
    @ManyToMany
    private List<Post> savedPost = new ArrayList<>();
}
