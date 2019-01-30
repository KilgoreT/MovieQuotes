package com.example.moviequotes.ui.QuoteDetails;

import com.example.moviequotes.base.MvpPresenter;

public interface QuoteDetailsMvpPresenter<V extends QuoteDetailsMvpView> extends MvpPresenter<V> {

    void messageDetaild(int id);

}
