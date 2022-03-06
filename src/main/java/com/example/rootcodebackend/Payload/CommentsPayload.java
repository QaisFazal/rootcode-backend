package com.example.rootcodebackend.Payload;

import com.example.rootcodebackend.model.Comments;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CommentsPayload {
    @JsonProperty(value = "comments")
    private List<Comments> comment;

    @JsonProperty(value = "count")
    private String count;

    public String getCount() {
        return count;
    }

    public List<Comments> getComment() {
        return comment;
    }

    public void setComment(List<Comments> comment) {
        this.comment = comment;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
