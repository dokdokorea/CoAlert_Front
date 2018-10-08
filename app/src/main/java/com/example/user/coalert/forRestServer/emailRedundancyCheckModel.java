package com.example.user.coalert.forRestServer;

public class emailRedundancyCheckModel {
    boolean emailCheck;
    String error;
    public boolean isEmailCheck() {
        return emailCheck;
    }

    public void setEmailCheck(boolean emailCheck) {
        this.emailCheck = emailCheck;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "{\"emailCheck\":\""+emailCheck+"\", \"error\" :\""+getError()+ "\"}";

    }
}

