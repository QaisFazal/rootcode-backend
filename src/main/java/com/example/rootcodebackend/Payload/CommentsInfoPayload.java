package com.example.rootcodebackend.Payload;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommentsInfoPayload {
    @JsonProperty(value = "comment")
    private String comment;

    @JsonProperty(value = "postId")
    private String postId;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }
}
