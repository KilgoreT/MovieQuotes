package com.example.moviequotes.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.moviequotes.R;
import com.example.moviequotes.base.BaseActivity;
import com.example.moviequotes.ui.ListQuote.ListQuoteFragment;
import com.example.moviequotes.ui.QuoteDetails.QuoteDetailsFragment;

import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements ListQuoteFragment.OnListQuoteFragmentListener {

    private ListQuoteFragment mListQuoteFragment;
    private QuoteDetailsFragment mQuoteDetailsFragment;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUnBinder(ButterKnife.bind(this));
        setFragment(getListQuoteFragment(), false);
    }

    private void setFragment(Fragment fragment, boolean onTop) {
        FragmentTransaction transaction = obtainFragmentManager()
                .beginTransaction();
        if (onTop) {
            transaction
                    .replace(R.id.fragment, fragment)
                    .addToBackStack(null);
        } else {
            transaction.replace(R.id.fragment, fragment);
        }
        transaction.commit();
    }

    private FragmentManager obtainFragmentManager() {
        if (mFragmentManager == null) {
            mFragmentManager = getSupportFragmentManager();
        }
        return mFragmentManager;
    }

    private ListQuoteFragment getListQuoteFragment() {
        if (mListQuoteFragment == null) {
            mListQuoteFragment = ListQuoteFragment.newInstance();
        }
        return mListQuoteFragment;
    }

    private QuoteDetailsFragment getQuoteDetailsFragment(int id) {
        if (mQuoteDetailsFragment == null) {
            mQuoteDetailsFragment = QuoteDetailsFragment.newInstance();
        }
        mQuoteDetailsFragment.setParamId(id);
        return mQuoteDetailsFragment;
    }

    @Override
    public void onQueryClick(int id) {
        setFragment(getQuoteDetailsFragment(id), true);
    }
}
