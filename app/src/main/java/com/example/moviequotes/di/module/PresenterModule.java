package com.example.moviequotes.di.module;

import com.example.moviequotes.repository.ServerApi;
import com.example.moviequotes.ui.ListQuote.ListQuoteMvpPresenter;
import com.example.moviequotes.ui.ListQuote.ListQuoteMvpView;
import com.example.moviequotes.ui.ListQuote.ListQuotePresenter;
import com.example.moviequotes.ui.QuoteDetails.QuoteDetailsMvpPresenter;
import com.example.moviequotes.ui.QuoteDetails.QuoteDetailsMvpView;
import com.example.moviequotes.ui.QuoteDetails.QuoteDetailsPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @Singleton
    @Provides
    public ListQuoteMvpPresenter<ListQuoteMvpView> provideListQuotePresenter(ServerApi api) {
        return new ListQuotePresenter<>(api);
    }


    @Singleton
    @Provides
    public QuoteDetailsMvpPresenter<QuoteDetailsMvpView> provideQuoteDetailsPresenter(ServerApi api) {
        return new QuoteDetailsPresenter<>(api);
    }
}
