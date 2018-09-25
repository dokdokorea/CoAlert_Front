package com.example.user.coalert.item;

public class ExplanationBadElementCardView {
    String rank;
    String element_name;

    public String getRank() {return this.rank;}
    public String getElement_name() {return this.element_name; }
    public ExplanationBadElementCardView(String rank,String element_name){
        this.rank=rank;
        this.element_name=element_name;
    }
}
