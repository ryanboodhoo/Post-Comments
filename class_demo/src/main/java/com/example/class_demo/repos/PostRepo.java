package com.example.class_demo.repos;

import com.example.class_demo.entities.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends CrudRepository<Post, Long>{
    @Query(value = "SELECT * FROM Post WHERE title LIKE CONCAT('%', :query, '%')", nativeQuery = true)

    Iterable<Post> searchPosts(String query);
}
