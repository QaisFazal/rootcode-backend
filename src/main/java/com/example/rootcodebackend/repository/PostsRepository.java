package com.example.rootcodebackend.repository;

import com.example.rootcodebackend.model.Posts;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface PostsRepository extends MongoRepository<Posts, String> {
    @Query("{name:'?0'}")
    Posts findPosts(String title);


}
