package com.example.moviequotes.ui.QuoteDetails;

import com.example.moviequotes.base.MvpFragmentView;
import com.example.moviequotes.repository.entity.QuoteDetailsItem;

public interface QuoteDetailsMvpView extends MvpFragmentView {
    void onReceiveResult(QuoteDetailsItem list);
    void onReceiveError(Throwable throwable);
}
