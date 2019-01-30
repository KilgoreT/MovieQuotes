package com.example.moviequotes.repository.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuoteDetailsResponse {


    @SerializedName("ok")
    @Expose
    private Boolean ok;
    @SerializedName("data")
    @Expose
    private List<QuoteDetailsItem> data = null;

    public Boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }

    public List<QuoteDetailsItem> getData() {
        return data;
    }

    public void setData(List<QuoteDetailsItem> data) {
        this.data = data;
    }

/*    boolean ok;
    List<Object> data;
//    QuoteDetailsItem data;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }*/
}
