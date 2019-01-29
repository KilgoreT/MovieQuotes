package com.example.moviequotes.ui.ListQuoteFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moviequotes.App;
import com.example.moviequotes.R;
import com.example.moviequotes.base.BaseFragment;
import com.example.moviequotes.repository.entity.QuoteItem;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListQuoteFragment extends BaseFragment implements ListQuoteMvpView, ListQuoteAdapter.Listener{

    @Inject
    ListQuoteMvpPresenter<ListQuoteMvpView> mPresenter;

    @BindView(R.id.list)
    public RecyclerView list;

    private ListQuoteAdapter mAdapter;
    private OnListQuoteFragmentListener mListener;

    public ListQuoteFragment() {
        // Required empty public constructor
    }


    public static ListQuoteFragment newInstance() {
        ListQuoteFragment fragment = new ListQuoteFragment();
        return fragment;
    }

    /*@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_list_quote, container, false);
        App.getInstance().getComponent().inject(this);
        setUnBinder(ButterKnife.bind(this, v));
        mPresenter.onAttach(this);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.onAttach(this);
        mAdapter = new ListQuoteAdapter();
        mAdapter.setListener(this);
        list = view.findViewById(R.id.list);
        list.setHasFixedSize(false);
        list.setAdapter(mAdapter);
        list.setLayoutManager(new LinearLayoutManager(view.getContext()));
        list.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int lastCompletelyVisibleItemPosition = 0;

                LinearLayoutManager layoutManager = LinearLayoutManager.class.cast(recyclerView.getLayoutManager());
                lastCompletelyVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                int totalItemCount = layoutManager.getItemCount();
                if (lastCompletelyVisibleItemPosition == totalItemCount - 1) {
                    list.post(new Runnable() {
                        @Override
                        public void run() {
                            mAdapter.showPreload(true);
                            mPresenter.loadNext();
                        }
                    });
                }
            }
        });
        mPresenter.loadNext();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListQuoteFragmentListener) {
            mListener = (OnListQuoteFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListQuoteFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public String getFragmentTag() {
        return null;
    }

    @Override
    protected void setUp(View view) {

    }

    @Override
    public void onReceiveResult(List<QuoteItem> items) {
        mAdapter.showPreload(false);
        mAdapter.setData(items);
    }

    @Override
    public void onReceiveError(Throwable throwable) {
        showSnackBar(throwable.getMessage());
    }

    @Override
    public void onItemClick(int id) {
        showSnackBar("Id = " + id);
    }

    public interface OnListQuoteFragmentListener {
        // TODO: Update argument type and name
        void onQueryClick(int id);
    }
}
