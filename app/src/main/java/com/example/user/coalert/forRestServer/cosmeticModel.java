package com.example.user.coalert.forRestServer;

public class cosmeticModel {
    String company;
    String cname;
    String ingr;

    String rank;
    String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getIngr() {
        return ingr;
    }

    public void setIngr(String ingr) {
        this.ingr = ingr;
    }


    @Override
    public String toString() {
        return "{cname: "+getCname()+", company:"+getCompany()
                +"ingr: "+getIngr()+", rank: "+getRank()+ ", error: "+getError()+"}";
    }
}
