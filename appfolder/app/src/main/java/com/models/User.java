package com.models;

import java.io.Serializable;

public class User implements Serializable {
    public String userName;
    public String uniqueId;
    public String imageUrl;
    public byte[] imageBytes;

    public User(String userName, String uniqueId, String imageUrl) {
        this.userName = userName;
        this.uniqueId = uniqueId;
        this.imageUrl = imageUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public byte[] getImageBytes() {
        return imageBytes;
    }

    public void setImageBytes(byte[] imageBytes) {
        this.imageBytes = imageBytes;
    }
}
