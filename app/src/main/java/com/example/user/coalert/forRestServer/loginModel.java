package com.example.user.coalert.forRestServer;

public class loginModel {
    String id;
    String password;

    public String getId() {
        return id;
    }

    public void setId(String id2) {
        this.id = id2;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "{password: "+getPassword()+", id: "+getId ()+"}";
    }
}
