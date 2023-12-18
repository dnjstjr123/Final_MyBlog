package com.example.myblog;

public class Post {
    private String title;
    private String text;
    private String created_date;
    private String published_date;
    private String image;

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getCreated_date() {
        return created_date; // 수정: created_date 필드 반환
    }

    public String getPublished_date() {
        return published_date; // 수정: published_date 필드 반환
    }

    public String getImage() {
        return image; // 수정: image 필드 반환
    }
}