package com.example.moviequotes.ui.ListQuote;

import com.example.moviequotes.base.MvpFragmentView;
import com.example.moviequotes.repository.entity.QuoteItem;

import java.util.List;

public interface ListQuoteMvpView extends MvpFragmentView {

    void onReceiveResult(List<QuoteItem> items);
    void onReceiveError(Throwable throwable);
    void onDoneRequest();
}
