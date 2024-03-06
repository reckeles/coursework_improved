package org.coursework.utils;

public enum HTMLTags {
    A("a"),
    SPAN("span"),
    P("p");

    private String tag;

    HTMLTags(String tag){
        this.tag = tag;
    }

    public String getName(){
        return tag;
    }
}
