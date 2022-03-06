package com.example.rootcodebackend.Payload;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PostsPayload {
        @JsonProperty(value = "title")
        private String title;

        @JsonProperty(value = "description")
        private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
