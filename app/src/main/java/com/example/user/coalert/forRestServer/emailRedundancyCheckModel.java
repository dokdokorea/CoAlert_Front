package com.example.user.coalert.forRestServer;

public class emailRedundancyCheckModel {
    boolean emailCheck;

    public boolean isEmailCheck() {
        return emailCheck;
    }

    public void setEmailCheck(boolean emailCheck) {
        this.emailCheck = emailCheck;
    }

    @Override
    public String toString() {
        return "{emailCheck:"+emailCheck+"}";
    }
}
