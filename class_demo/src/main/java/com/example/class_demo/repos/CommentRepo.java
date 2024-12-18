package com.example.class_demo.repos;

import com.example.class_demo.entities.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo extends CrudRepository<Comment, Long> {
    Comment findByIdAndPostId(Long id, Long postId);

    Iterable<Comment> findByPostId(Long postId);


}
