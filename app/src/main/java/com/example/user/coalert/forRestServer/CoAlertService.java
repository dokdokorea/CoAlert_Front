package com.example.user.coalert.forRestServer;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CoAlertService {
    @POST("login")
    Call<loginModel> loginCall(
            @Query("id") String id,
            @Query("password") String password,
            @Query("imei")String imei
    );
    @POST("cosmetic")
    Call<cosmeticModel>  cosmeicCall(
            @Query("COMPANY") String company,
            @Query("CNAME") String cname,
            @Query("INGR") String ingr,
            @Query("CATEGORY") String category
    );
    @POST("review")
    Call<ReviewModel> reviewCall(
            @Query("COMPANY") String company,
            @Query("CNAME") String cname,
            @Query("INGR") String ingr, //성분
            @Query("CONTENT") String content, //자세한 리뷰
            @Query("CATEGORY") String category,
            @Query("ONELINE") String oneline, //한줄리뷰
            @Query("IMEI") String imei
    );

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.43.153:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
