package com.example.class_demo.Service;

import com.example.class_demo.entities.Post;
import com.example.class_demo.entities.User;
import com.example.class_demo.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

//BIZ LOGIC
public class UserService {

    //create post
//    public void addPost(Post post){
//        postRepo.save(post);
//    }
    @Autowired
    UserRepo userRepo;

    public User addNewUser(User user) {
        return userRepo.save(user);
    }


    public Iterable<Post> getAllPostByUser(String query) {
        return userRepo.searchPosts(query);
    }
}



