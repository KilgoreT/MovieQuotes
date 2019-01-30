package com.example.moviequotes.ui.ListQuote;

import com.example.moviequotes.base.BasePresenter;
import com.example.moviequotes.repository.ServerApi;
import com.example.moviequotes.repository.entity.QuoteItem;
import com.example.moviequotes.repository.entity.QuoteList;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class ListQuotePresenter<V extends ListQuoteMvpView> extends BasePresenter<V> implements ListQuoteMvpPresenter<V> {

    private static final int LIMIT = 10;

    private ServerApi mApi;
    private List<QuoteItem> mData = new ArrayList<>();
    private int mOffset = 1;
    private CompositeDisposable loadSub = new CompositeDisposable();
    private final AtomicBoolean done = new AtomicBoolean(false);
    private final AtomicBoolean reloadData = new AtomicBoolean();

    public ListQuotePresenter(ServerApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void onAttach(V mvpFragmentView) {
        super.onAttach(mvpFragmentView);
        if (mData.size() > 0 && getView() != null) {
            getView().onReceiveResult(mData);
        }
    }

    public List<QuoteItem> getData() {
        return mData;
    }

    @Override
    public int getDataCount() {
        return mData.size();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (loadSub.isDisposed()) {
            loadSub.clear();
        }
    }

    @Override
    public void loadNext() {

        if (done.get() || loadSub != null && loadSub.isDisposed()) {
            return;
        }

        loadSub.clear();

        if (reloadData.get()) {
            mOffset = 1;
        }

        reloadData.set(false);

        Disposable d = mApi.messages(mOffset, LIMIT)
                .delay(800, TimeUnit.MILLISECONDS)
                .map(new Function<QuoteList, List<QuoteItem>>() {
                    @Override
                    public List<QuoteItem> apply(QuoteList quoteList) throws Exception {
                        done.set(quoteList.getData().size() < LIMIT);
                        return quoteList.getData();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<QuoteItem>>() {
                    @Override
                    public void accept(List<QuoteItem> items) throws Exception {
                        mData.addAll(mOffset - 1, items);
                        mOffset += items.size();
                        if (getView() != null) {
                            getView().onReceiveResult(mData);
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
                loadSub.add(d);

    }

    @Override
    public void reloadData() {
        if (loadSub != null && !loadSub.isDisposed()) {
            loadSub.clear();
        }
        reloadData.set(true);
        done.set(false);
        mData.clear();
        loadNext();
    }

}
