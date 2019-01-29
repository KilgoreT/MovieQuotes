package com.example.moviequotes.repository.entity;

import java.util.List;

public class QuoteList {

    boolean ok;
    List<QuoteItem> data;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public List<QuoteItem> getData() {
        return data;
    }

    public void setData(List<QuoteItem> data) {
        this.data = data;
    }
}
