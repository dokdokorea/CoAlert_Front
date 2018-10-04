package com.example.user.coalert.forRestServer;

public class signUpModel {
    String id, error;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "{id:"+getId()+",error:"+getError()+"}";
    }
}
