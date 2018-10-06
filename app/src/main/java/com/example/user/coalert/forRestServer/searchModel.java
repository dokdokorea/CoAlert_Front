package com.example.user.coalert.forRestServer;

import com.example.user.coalert.Background;
import com.example.user.coalert.Singleton.ForBackgroundSingleton;

import java.util.List;

public class searchModel {
    List<String> cname, company, name;
    String error;

    Background variable = ForBackgroundSingleton.getInstance();

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<String> getCname() {
        this.setCname(cname);
        return cname;
    }

    public void setCname(List<String> cname) {
        variable.listCname = cname;
    }

    public List<String> getCompany() {
        this.setCompany(company);
        return company;
    }

    public void setCompany(List<String> company) {
        variable.listCompany = company;
    }

    public List<String> getName() {
        this.setName(name);
        return name;
    }

    public void setName(List<String> name) {
        variable.listName = name;
    }

    @Override
    public String toString() {
        return "{cname:"+getCname()+", company:"+getCompany()+", name:"+getName()+", error"+getError()+"}";
        }
}
