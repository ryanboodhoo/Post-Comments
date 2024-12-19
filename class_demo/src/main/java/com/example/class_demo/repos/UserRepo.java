package com.example.class_demo.repos;

import com.example.class_demo.entities.Post;
import com.example.class_demo.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepo extends CrudRepository<User, Long> {
    @Query(value = "SELECT * FROM Post WHERE title LIKE CONCAT('%', :query, '%')", nativeQuery = true)
    Iterable<Post> searchPosts(String query);

   // public List<Post> findByTitle(@Param("title")String title);
}
