package com.example.user.coalert.forRestServer;

public class SimpleReviewModel {
    String cname;
    String content;
    String oneline;
    String rate;
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

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "{cname:" + getCname() + ", content: " + getContent() + ", oneline: " + getOneline() + ", rate:" + getRate()+"error:"+getError()+"}";
    }
}
