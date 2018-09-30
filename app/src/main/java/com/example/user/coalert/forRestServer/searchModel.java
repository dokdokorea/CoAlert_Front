package com.example.user.coalert.forRestServer;

public class searchModel {
    String search;

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    @Override
    public String toString() {
        return "{search:"+getSearch()+"}";
    }
}
