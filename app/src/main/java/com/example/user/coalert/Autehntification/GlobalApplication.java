package com.example.user.coalert.Autehntification;

import android.app.Activity;
import android.app.Application;
import android.graphics.Bitmap;

import com.example.user.coalert.R;
import com.kakao.auth.KakaoSDK;

import java.util.ArrayList;

public class GlobalApplication extends Application {

    private static volatile GlobalApplication obj = null;
    private static volatile Activity currentActivity = null;

    private String Id;
    private static ArrayList<String> wishlist =new ArrayList<>();
    private static ArrayList<String> complist=new ArrayList<>();
    private ArrayList<Integer> cosphoto=new ArrayList<>();
    private ArrayList<Bitmap> image=new ArrayList<>();

    public static ArrayList<String> getComplist() {
        return complist;
    }

    public static void setComplist(ArrayList<String> complist) {
        GlobalApplication.complist = complist;
    }

    public void addCompany(String wishitem) {
        this.complist.add(wishitem);
    }

    public void removeCompany(String wishitem) {
        this.complist.remove(wishitem);
    }

    public ArrayList<Bitmap> getImage() {
        return image;
    }

    public void setImage(ArrayList<Bitmap> image) {
        this.image = image;
    }

    public void addImage(Bitmap wishitem) {
        this.image.add(wishitem);
    }

    public void removeImage(String wishitem) {
        this.image.remove(wishitem);
    }

    public ArrayList<String> getWishlist() {
        return wishlist;
    }

    public void setWishlist(ArrayList<String> wishlist) {
        this.wishlist = wishlist;
    }

    public void addWishlist(String wishitem) {
        this.wishlist.add(wishitem);
    }

    public void removeWishlist(String wishitem) {
        this.wishlist.remove(wishitem);
    }

    public void addcosphoto(Integer wishitem) {
        this.cosphoto.add(wishitem);
    }

    public void removecosphoto(Integer wishitem) {
        this.cosphoto.remove(wishitem);
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    private String Email;


    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        obj = this;
        KakaoSDK.init(new KakaoSDKAdapter());
    }

    public static GlobalApplication getGlobalApplicationContext() {
        return obj;
    }

    public static Activity getCurrentActivity() {
        return currentActivity;
    }

    // Activity가 올라올때마다 Activity의 onCreate에서 호출해줘야한다.
    public static void setCurrentActivity(Activity currentActivity) {
        GlobalApplication.currentActivity = currentActivity;
    }
}