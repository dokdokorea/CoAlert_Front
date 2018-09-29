package com.example.user.coalert.forRestServer;

public class cosmeticModel {
    String company;
    String cname;
    String ingr;
    String category;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "입력된 화장품 정보: "+"화장품 이름: "+getCname()+" 화장품 회사"+getCompany()
                +"화장품 성분: "+getIngr()+" category: "+getCategory();
    }
}
