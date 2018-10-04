package com.example.user.coalert.forRestServer;

public class DetailedReviewModel {
    String lcode;
    String id;
    String cname;
    String title;
    String content;
    String like;

    public String getLcode() {
        return lcode;
    }

    public void setLcode(String lcode) {
        this.lcode = lcode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

//    String lcode;
//    String id;
//    String cname;
//    String title;
//    String content;
//    String like;
//
    @Override
    public String toString() {
        return "{locode: "+getLcode()+", id:"+getId()+", cname:"+getCname()+", title:"+getTitle()+", content:"+getContent()+", like:"+getLike()+"}";
    }
}
