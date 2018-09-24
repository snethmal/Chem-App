package com.example.supunnethmal.slide.API;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserClient {

    @POST("/analyze")
    Call<User> analyzeText(@Body User user);
}
