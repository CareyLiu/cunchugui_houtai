package com.cunchugui.houtai.model;

public class FuGuiModel {
    private String name;
    private String start;

    public FuGuiModel(String name, String start) {
        this.name = name;
        this.start = start;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }
}
