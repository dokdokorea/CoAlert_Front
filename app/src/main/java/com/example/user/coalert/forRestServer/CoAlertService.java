package com.example.user.coalert.forRestServer;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CoAlertService {
    @POST("login")
    Call<loginModel> loginCall(
            @Query("id") String id,
            @Query("password") String password
    );

    @POST("cosmetic_info")
    Call<cosmeticModel> cosmeicCall(
            @Query("search") String search,
            @Query("id") String id,
            @Query("session") String session1
            );

    @POST("get_simple")
    Call<SimpleReviewModel> simpleReviewCall(
            @Query("id") String id,
            @Query("cname") String cname,
            @Query("session") String session,
            @Query("start") int start
    );

    @POST("get_detailed")
    Call<SimpleReviewModel> detailReviewCall(
            @Query("id") String id,
            @Query("cname") String cname,
            @Query("session") String session,
            @Query("start") int start
    );

    @POST("search_bar")
    Call<searchModel> searchCall(
            @Query("search") String text,
            @Query("id") String id,
            @Query("session") String session
    );

    @POST("get_user")
    Call<GetUserModel> getUserCall(
            @Query("search") String text,
            @Query("id") String id,
            @Query("session") String session
    );

    @POST("signup")
    Call<signInModel> signUpCall(
            @Query("id") String id,
            @Query("password") String password,
            @Query("name") String name,
            @Query("email") String email,
            @Query("type") String type,
            @Query("birth") String birth,
            @Query("sex") String sex,
            @Query("access") String access
    );

    @POST("idcheck")
    Call<emailRedundancyCheckModel> emailCheck(
            @Query("eamil") String email
    );

    @POST("recommendCosmetic")
    Call<List<getRecommendModel>> recommendCall(
            @Query("persontype") int personType,
            @Query("cosmetictype") int cosmeticType,
            @Query("imei") String imei,
            @Query("id") String id
    );


    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://172.30.1.17:5000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
