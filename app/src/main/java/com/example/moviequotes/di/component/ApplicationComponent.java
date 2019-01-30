package com.example.moviequotes.di.component;

import com.example.moviequotes.di.module.ApplicationModule;
import com.example.moviequotes.di.module.PresenterModule;
import com.example.moviequotes.ui.ListQuote.ListQuoteFragment;
import com.example.moviequotes.ui.QuoteDetails.QuoteDetailsFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, PresenterModule.class})
public interface ApplicationComponent {

    void inject(ListQuoteFragment targer);
    void inject(QuoteDetailsFragment targer);

}
