package com.example.user.coalert.forRestServer;

public class loginModel {
    String id;
    String session;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    @Override
    public String toString() {
        return "{password: "+getSession()+", id: "+getId ()+"}";
    }
}
