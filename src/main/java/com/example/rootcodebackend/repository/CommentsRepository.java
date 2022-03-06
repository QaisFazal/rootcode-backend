package com.example.rootcodebackend.repository;

import com.example.rootcodebackend.model.Comments;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CommentsRepository extends MongoRepository<Comments, String> {
    @Query(value = "{postId:'?0'}")
    List<Comments> findByPostsId(String postId);
}
