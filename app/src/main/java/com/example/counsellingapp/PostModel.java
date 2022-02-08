package com.example.counsellingapp;

public class PostModel {
    private String ImageUrl, description;

    public PostModel(String imageUrl, String description) {
        ImageUrl = imageUrl;
        this.description = description;
    }

    public PostModel() {
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
