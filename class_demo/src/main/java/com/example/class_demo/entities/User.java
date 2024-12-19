package com.example.class_demo.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class User {

    // @OneToMany
    // one user can have a multiple post
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="USER_ID")
    private Long id;
    private String name;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Post> posts;

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
