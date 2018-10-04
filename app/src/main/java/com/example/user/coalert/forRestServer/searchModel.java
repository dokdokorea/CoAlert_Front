package com.example.user.coalert.forRestServer;

public class searchModel {
    String cname;
    String company;
    String name;
    String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "{cname:"+getCname()+", company:"+getCompany()+", name:"+getName()+", error"+getError()+"}";
    }
}
