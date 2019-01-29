package com.example.moviequotes.ui.ListQuoteFragment;

import com.example.moviequotes.base.MvpPresenter;

public interface ListQuoteMvpPresenter<V extends ListQuoteMvpView> extends MvpPresenter<V> {
    void loadNext();
    void reloadData();
}
