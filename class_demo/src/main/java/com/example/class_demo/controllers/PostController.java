package com.example.class_demo.controllers;


import com.example.class_demo.Service.PostService;
import com.example.class_demo.entities.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class PostController {

    private final Logger logger = LoggerFactory.getLogger(PostController.class);

    @Autowired
    private PostService postService;


    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @PostMapping("/make/posts")
    public ResponseEntity<Void> createPost(@RequestBody Post post) {
        postService.addPost(post);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId())
                .toUri();
        logger.info("Created a new post with an id of " + post.getId());
        return ResponseEntity.created(location).build();
    }

    //Update a Post
    @PutMapping("/posts/{id}")
    public ResponseEntity<?> changePost(@RequestBody Post post, @PathVariable Long id) {
        return postService.changePost(post, id);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<?> getASinglePost(@PathVariable Long id) {
        return postService.getAPostById(id);

    }

    @GetMapping("/searchpost")
    public Iterable <Post> searchPost(@RequestParam("query") String query){
        return postService.getAllPostByTitle(query);
    }
}




