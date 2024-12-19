package com.example.class_demo.controllers;

import com.example.class_demo.Exception.ResourceNotFoundException;
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

    //Get a comment  - throw a resource not found exception
    @GetMapping("/comments/{commentId}")
    public ResponseEntity<?> getCommentById(@PathVariable Long commentId) {
        ResponseEntity<?> comment = commentService.getACommentById(commentId);
        if (comment == null) {
            throw new ResourceNotFoundException("Comment not found with ID: " + commentId);
        }
        return comment;
    }
    //getAllCommentsByPostId
@GetMapping("/comments/{id}/comments")
    public Iterable <Comment> getAllCommentsByPostId(@PathVariable Long id){
        return  commentService.getAllCommentsByPostId(id);
}
}

