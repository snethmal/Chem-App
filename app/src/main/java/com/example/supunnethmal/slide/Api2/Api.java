package com.example.supunnethmal.slide.Api2;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by supun nethmal on 23-Sep-18.
 */

public interface Api {
    @POST("/lang")
    Call<ResponseBody> lang(@Body RequestBody requestBody);
}
