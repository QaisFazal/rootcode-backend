package com.example.rootcodebackend;

import com.example.rootcodebackend.Payload.CommentsInfoPayload;
import com.example.rootcodebackend.Payload.CommentsPayload;
import com.example.rootcodebackend.Payload.PostsPayload;
import com.example.rootcodebackend.model.Comments;
import com.example.rootcodebackend.repository.CommentsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class CommentsController {

    @Autowired
    CommentsRepository commentsRepository;

    @CrossOrigin
    @PostMapping("/comments/{id}")
    public CommentsPayload getCount(@PathVariable String id) {

        if(id == null || id.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Missing Post Id for comments.");
        }
        List<Comments> commentsList;
        try {
            commentsList = commentsRepository.findByPostsId(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while retrieving from Mongo");
        }
            CommentsPayload commentsPayload = new CommentsPayload();
            commentsPayload.setComment(commentsList);
            commentsPayload.setCount(String.valueOf(commentsList.size()));
            ObjectMapper mapper = new ObjectMapper();
            try {
                return mapper.convertValue(commentsPayload, CommentsPayload.class);
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error during processing JSON Payload");
            }

    }

    @CrossOrigin
    @PostMapping("/comments")
    public HttpStatus saveComment(@RequestBody String payload) throws JsonProcessingException {
        ObjectMapper mapper;
        CommentsInfoPayload comment;
        try {
            mapper = new ObjectMapper();
            comment = mapper.readValue(payload, CommentsInfoPayload.class);
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Error processing JSON payload");
        }
        Comments comments = new Comments();
        if(comment.getComment() == null || comment.getComment().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing comment.");
        }
        if(comment.getPostId() == null || comment.getPostId().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing post Id.");
        }
        comments.setComment(comment.getComment());
        comments.setPostId(comment.getPostId());

        try {
            commentsRepository.save(comments);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Error while saving the comments");
        }
        return HttpStatus.CREATED;
    }
}
