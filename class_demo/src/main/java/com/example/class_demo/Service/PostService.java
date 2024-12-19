package com.example.class_demo.Service;

import com.example.class_demo.Exception.ResourceNotFoundException;
import com.example.class_demo.entities.Post;
import com.example.class_demo.entities.User;
import com.example.class_demo.repos.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepo postRepo;

    public List<Post> getAllPosts(){
        List<Post> postList = new ArrayList<>();
        for(Post post : postRepo.findAll()){
            postList.add(post);
        }
         return postList;
    }

    //create post
    public void addPost(Post post){
        postRepo.save(post);
    }
    //DELETE POST
    public void deletePost(Long id){
    postRepo.deleteById(id);
    }

     public ResponseEntity<?> getAPostById(Long postId)throws ResourceNotFoundException  {
         Post post = postRepo.findById(postId).orElse(null);

         if (post == null) {
             throw new ResourceNotFoundException("Post with id of " + postId + " not found");
         }
         return new ResponseEntity<> (post, HttpStatus.OK);
     }

    //Update a Post
    public ResponseEntity<?> changePost(Post post, Long id){

        if (postRepo.existsById(id)){
            Post newPost = postRepo.findById(id).get();
            newPost.setTitle(post.getTitle());
            newPost.setContent(post.getContent());;
            return new ResponseEntity<>(postRepo.save(newPost), HttpStatus.ACCEPTED);
        }
        return null;
    }

    public Iterable<Post> getAllPostByTitle (String query) {
       return postRepo.searchPosts(query);
     }

     public List<Post>findByTitle(String title){
        return postRepo.findByTitle(title);
     }


        public Post createPostFromUser(User user) {
            Post post = new Post(user);
            return postRepo.save(post);
        }


}