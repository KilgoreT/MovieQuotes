package com.example.moviequotes.base;

import android.app.Activity;
import android.content.Context;

public interface MvpFragmentView extends MvpView {
    Activity getFragmentActivity();
    Context getFragmentContext();
}
