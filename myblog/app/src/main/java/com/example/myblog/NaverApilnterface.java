package com.example.myblog;

import okhttp3.MultipartBody;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.Call;

interface NaverApiInterface {
    @Multipart
    @POST("/v1/vision/face")
    Call<NaverRepo> naverRepo(@Header("X-Naver-Client-Id") String id
            ,@Header("X-Naver-Client-Secret") String secret
            ,@Part MultipartBody.Part file);
}