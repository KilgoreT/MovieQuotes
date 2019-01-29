package com.example.moviequotes.di.module;

import com.example.moviequotes.repository.ServerApi;
import com.example.moviequotes.ui.ListQuoteFragment.ListQuoteMvpPresenter;
import com.example.moviequotes.ui.ListQuoteFragment.ListQuoteMvpView;
import com.example.moviequotes.ui.ListQuoteFragment.ListQuotePresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @Singleton
    @Provides
    public ListQuoteMvpPresenter<ListQuoteMvpView> provideSpeedPresenter(ServerApi api) {
        return new ListQuotePresenter<>(api);
    }
}
