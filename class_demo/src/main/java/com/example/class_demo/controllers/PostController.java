package com.example.class_demo.controllers;


import com.example.class_demo.Service.PostService;
import com.example.class_demo.entities.Post;
import com.example.class_demo.entities.User;
import jakarta.validation.Valid;
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

    @PostMapping("/posts")
    public void createPost(@RequestBody Post post) {
        postService.addPost(post);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId())
                .toUri();
        logger.info("Created a new post with an id of " + post.getId());
        postService.addPost(post);
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

    @GetMapping("/post/search")
    public List<Post> getAllPostByTitle(@RequestParam(name = "titles") String title) {
        return postService.findByTitle(title);
    }

    //getAllPostByTitle using Query Parameters = @RequestParam
    @GetMapping("/searchpost")
    public Iterable<Post> searchPostByTitle(@RequestParam("query") String query) {
        return postService.getAllPostByTitle(query);
    }

    //delete a post
    @DeleteMapping("/posts/{postId}")
    public void deletePost(@PathVariable Long postId) {
        logger.info("Deleted a post with an id of " + postId);
        postService.deletePost(postId);
    }

}





