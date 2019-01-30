package com.example.moviequotes.repository.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TagItem {

    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("color")
    @Expose
    private String color;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
