package com.example.rootcodebackend;

import com.example.rootcodebackend.Payload.PostsPayload;
import com.example.rootcodebackend.model.Posts;
import com.example.rootcodebackend.repository.PostsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class PostsController {

    @Autowired
    PostsRepository postsRepository;


    @CrossOrigin
    @PostMapping("/posts")
    public String save(@RequestBody String payload) {
        PostsPayload post;
        try {
            ObjectMapper mapper = new ObjectMapper();
            post = mapper.readValue(payload, PostsPayload.class);

        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error during processing the JSON", e);
        }
        if (post.getTitle() == null || post.getTitle().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing title.");
        }
        if (post.getDescription() == null || post.getDescription().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing description.");
        }
        try {
            postsRepository.save(new Posts(post.getTitle(), post.getDescription()));

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error during processing the JSON", e);
        }
        return "success";
    }


    @CrossOrigin
    @GetMapping("/posts")
    public String findAll() {
        ObjectMapper mapper;
        List<Posts> posts;
        try {
            posts = postsRepository.findAll(Sort.by(Sort.Direction.DESC, "_id"));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error during retrieving from Mongo.");
        }
        try {
            mapper = new ObjectMapper();
            return mapper.writeValueAsString(posts);
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error during processing JSON.");
        }

    }
}
