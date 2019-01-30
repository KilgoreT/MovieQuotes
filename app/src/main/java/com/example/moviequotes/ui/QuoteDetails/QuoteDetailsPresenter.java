package com.example.moviequotes.ui.QuoteDetails;

import com.example.moviequotes.base.BasePresenter;
import com.example.moviequotes.repository.ServerApi;
import com.example.moviequotes.repository.entity.QuoteDetailsItem;
import com.example.moviequotes.repository.entity.QuoteDetailsResponse;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class QuoteDetailsPresenter<V extends QuoteDetailsMvpView> extends BasePresenter<V> implements QuoteDetailsMvpPresenter<V> {

    private ServerApi mApi;
    private CompositeDisposable subs = new CompositeDisposable();

    public QuoteDetailsPresenter(ServerApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void messageDetaild(int id) {
        subs.clear();
        Disposable d = mApi.messageDetails(id)
                .map(new Function<QuoteDetailsResponse, List<QuoteDetailsItem>>() {
                    @Override
                    public List<QuoteDetailsItem> apply(QuoteDetailsResponse quoteDetailsResponse) throws Exception {
                        if (quoteDetailsResponse.getOk()) {
                            return quoteDetailsResponse.getData();
                        } else {
                            throw new IllegalArgumentException("Request error");
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<QuoteDetailsItem>>() {
                    @Override
                    public void accept(List<QuoteDetailsItem> list) throws Exception {
                        if (getView() != null) {
                            getView().onReceiveResult(list.get(0));
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (getView() != null) {
                            getView().onReceiveError(throwable);
                        }
                    }
                });
        subs.add(d);
    }
}
