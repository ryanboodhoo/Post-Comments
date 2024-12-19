package com.example.class_demo.entities;

import jakarta.persistence.*;

@Entity
public class User {

    // @ManyToOne
    // one user can have a multiple post
    @OneToMany(cascade= CascadeType.ALL)
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;

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
