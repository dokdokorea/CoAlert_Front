package com.example.user.coalert.forRestServer;

public class loginModel {
    String id;
    String session;
    String error;
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

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "{session: "+getSession()+", id: "+getId ()+", error:"+getError()+"}";
    }
}
