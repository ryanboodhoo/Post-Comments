package com.example.class_demo.Service;

import com.example.class_demo.Exception.ResourceNotFoundException;
import com.example.class_demo.entities.Comment;
import com.example.class_demo.entities.Post;
import com.example.class_demo.repos.CommentRepo;
import com.example.class_demo.repos.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepo postRepo;

    public void addComment(Long postId, Comment comment) {

        Post post = postRepo.findById(postId).get();
         comment.setPost(post);
         commentRepo.save(comment);

    }

    public void deleteComment( Long commentId, Long postId){
        Comment comment =  commentRepo.findByIdAndPostId(commentId, postId);
        commentRepo.delete(comment);

    }

    public Iterable<Comment> getAllComments() {
        return commentRepo.findAll();
    }


    public ResponseEntity<?> getACommentById(Long commentId)throws ResourceNotFoundException {
        Comment comment = commentRepo.findById(commentId).orElse(null);

        if (comment == null) {
            throw new ResourceNotFoundException("Comment with id of " + commentId + " not found");
        }
        return new ResponseEntity<> (comment, HttpStatus.OK);
    }

    public Iterable<Comment>getAllCommentsByPostId(Long commentId){
    return commentRepo.findByPostId(commentId);
 }




}