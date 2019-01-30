package com.example.moviequotes.ui.ListQuote;

import com.example.moviequotes.base.MvpPresenter;
import com.example.moviequotes.repository.entity.QuoteItem;

import java.util.List;

public interface ListQuoteMvpPresenter<V extends ListQuoteMvpView> extends MvpPresenter<V> {

    List<QuoteItem> getData();
    int getDataCount();
    void loadNext();
    void reloadData();

}
