package com.example.moviequotes.base;

import android.util.Log;

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private V mMvpView;

    @Override
    public V getView() {
        return mMvpView;
    }

    @Override
    public void onAttach(V mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void onDetach() {
        mMvpView = null;
    }

    @Override
    public void showLog(String message) {
        Log.d("###", "<" + getClass().getSimpleName() + ">: " + message);
    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }
}
