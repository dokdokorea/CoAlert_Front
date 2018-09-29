package com.example.user.coalert.forRestServer;

public class ReviewModel {
    String cname;
    String content;
    String oneline;

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOneline() {
        return oneline;
    }

    public void setOneline(String oneline) {
        this.oneline = oneline;
    }

    @Override
    public String toString() {
        return "입력된 리뷰: "+"화장품: "+getCname()+" 한줄평 "+getOneline()+" 상세 리뷰: "+getContent();
    }
}
