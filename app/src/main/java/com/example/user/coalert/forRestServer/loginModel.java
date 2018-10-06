package com.example.user.coalert.forRestServer;

import com.example.user.coalert.Background;
import com.example.user.coalert.Singleton.ForBackgroundSingleton;

public class loginModel {
    String id;
    String session;
    String error,email,name;

    Background variable = ForBackgroundSingleton.getInstance();

    public String getId() {
        this.setId(id);
        return id;
    }

    public String getEmail(){
        return email;
    }

    public void setId(String id) {
        variable.id = id;
    }

    public String getSession() {
        this.setSession(session);
        return session;
    }

    public void setSession(String session) {
        variable.session = session;
    }

    public String getError() {
        return error;
    }

    public String getName() {
        return name;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "{session: " + getSession() + ", id: " + getId() + ", name:" + getName() + ", error:" + getError() + "}";
    }
}