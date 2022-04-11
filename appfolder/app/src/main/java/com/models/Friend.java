package com.models;

import java.io.Serializable;

public class Friend implements Serializable {
    public String userName;
    public String uniqueId;
    public int image;
    public byte[] imageBytes;

    public Friend(String userName, String uniqueId, int image) {
        this.userName = userName;
        this.uniqueId = uniqueId;
        this.image = image;
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

    public int getImageUrl() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public byte[] getImageBytes() {
        return imageBytes;
    }

    public void setImageBytes(byte[] imageBytes) {
        this.imageBytes = imageBytes;
    }
}
