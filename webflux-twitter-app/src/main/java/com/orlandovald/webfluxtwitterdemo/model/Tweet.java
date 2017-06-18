package com.orlandovald.webfluxtwitterdemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author ovaldez
 */
@Document(collection = "tweets")
public class Tweet {

    @Id
    @JsonProperty("id_str")
    private String id;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("text")
    private String text;

    @JsonProperty("user")
    private User user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "id='" + id + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", text='" + text + '\'' +
                ", user=" + user +
                '}';
    }
}
