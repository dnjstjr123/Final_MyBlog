package com.example.myblog;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface GetAPI {
    @GET("/api_root/Post/")
    Call<List<Post>> getPosts();
}

