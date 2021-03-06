package com.example.user.coalert.forRestServer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CoAlertService {
    Gson gson = new GsonBuilder()
            .setLenient()
            .create();

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
    Call<signUpModel> signUpCall(
            @Query("id") String id,
            @Query("password") String password,
            @Query("name") String name,
            @Query("email") String email,
            @Query("type") String type,
            @Query("birth") String birth,
            @Query("sex") CharSequence sex,
            @Query("access") int access
    );

    @POST("idcheck")
    Call<emailRedundancyCheckModel> emailCheck(
            @Query("id") String email
    );

    @POST("recommendCosmetic")
    Call<List<getRecommendModel>> recommendCall(
            @Query("persontype") int personType,
            @Query("cosmetictype") int cosmeticType,
            @Query("id") String id,
            @Query("start") int start
    );

    @POST("badIngredient")
    Call<List<GetBadIngredientModel>> getBadIngredient(
            @Query("id") String id,
            @Query("person_type") String type
    );

    @POST("ingredientPerCosmetic")
    Call<List<GetBadIngredientModel>> ingredientPerCosmetic(
            @Query("cname") String cname,
            @Query("kind") String kind
    );

    @POST("oneCosmeticRating")
    Call<oneCosmeticRecommend> oneRecommendCosmetic(
            @Query("kind") int kind,
            @Query("cname") String cname,
            @Query("type") int type
    );

    @POST("getReview")
    Call<List<getReviewModel>> getReview(
            @Query("kind") int kind,
            @Query("cname") String cname

    );

    @POST("setReview")
    Call<setReviewModel> setReview(
            @Query("id") int id,
            @Query("kindCosmetic") int kind,
            @Query("cname") String cname,
            @Query("rating") int rating,
            @Query("type") int type,
            @Query("review") String text
    );

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.43.85:5000/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
}
