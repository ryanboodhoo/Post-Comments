package com.example.class_demo.controllers;

import com.example.class_demo.Service.CommentService;
import com.example.class_demo.entities.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {


    @Autowired
    private CommentService commentService;

    @PostMapping("/comments/{postId}/comments")
    public void addCommentToPost(@PathVariable Long postId, @RequestBody Comment comment){
        commentService.addComment(postId, comment);
    }

    //Delete a comment
    @DeleteMapping("/comments/{commentId}/posts/{postId}")
    public void deleteComment(@PathVariable Long commentId, @PathVariable Long postId){
        commentService.deleteComment(commentId, postId);
    }

    //Get All comments
    @GetMapping("/comments")
    public Iterable<Comment> getAllComments() {
         return commentService.getAllComments();
    }

    //Get All comments
    @GetMapping("/comments/{id}")
    public ResponseEntity<?> getACommentById(@PathVariable Long id) {
        return commentService.getACommentById(id);
    }

@GetMapping("/comments/{id}/comments")
    public Iterable <Comment> getAllCommentsByPostId(@PathVariable Long id){
        return  commentService.getAllCommentsByPostId(id);
}
}

