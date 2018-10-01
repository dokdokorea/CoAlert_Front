package com.example.user.coalert.forRestServer;

import java.util.List;

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
            @Query("imei") String imei
    );

    @POST("cosmetic")
    Call<cosmeticModel> cosmeicCall(
            @Query("company") String company,
            @Query("cname") String cname,
            @Query("ingr") String ingr,
            @Query("category") String category
    );

    @POST("review")
    Call<ReviewModel> reviewCall(
            @Query("company") String company,
            @Query("cname") String cname,
            @Query("ingr") String ingr, //성분
            @Query("content") String content, //자세한 리뷰
            @Query("category") String category,
            @Query("oneline") String oneline, //한줄리뷰
            @Query("imei") String imei
    );

    @GET("search")
    Call<searchModel> searchCall(
            @Query("text") String text
    );

    @POST("signin")
    Call<signInModel> signInCall(
            @Query("id") String id,
            @Query("password") String password,
            @Query("name") String name,
            @Query("email") String email,
            @Query("type")String type,
            @Query("birth")String birth,
            @Query("sex")String sex,
            @Query("access")String access
    );
    @POST("recommendCosmetic")
    Call<List<getRecommendModel>> recommendCall(
            @Query("persontype") int personType,
            @Query("cosmetictype") int cosmeticType,
            @Query("imei") String imei,
            @Query("id") String id
    );


    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.43.153:5000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
