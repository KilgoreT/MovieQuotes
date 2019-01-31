package com.example.moviequotes.base;

public interface MvpPresenter<V extends MvpView> {

    V getView();
    void onAttach(V mvpView);
    void onDetach();
    void showLog(String message);
}
