package com.example.user.coalert.forRestServer;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CoAlertService {
    @POST("Login")
    Call<loginModel> call(@Query("id") String id,
            @Query("password") String password);


    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://localhost:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
