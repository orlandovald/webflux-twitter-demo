package com.orlandovald.webfluxtwitterdemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ovaldez
 */
public class User {

    @JsonProperty("id_str")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("profile_image_url")
    private String profileImageUrl;

    @JsonProperty("profile_image_url_https")
    private String profileImageUrlHttps;

    @JsonProperty("screen_name")
    private String screenName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getProfileImageUrlHttps() {
        return profileImageUrlHttps;
    }

    public void setProfileImageUrlHttps(String profileImageUrlHttps) {
        this.profileImageUrlHttps = profileImageUrlHttps;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", profileImageUrl='" + profileImageUrl + '\'' +
                ", profileImageUrlHttps='" + profileImageUrlHttps + '\'' +
                ", screenName='" + screenName + '\'' +
                '}';
    }
}
