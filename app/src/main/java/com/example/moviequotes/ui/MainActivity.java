package com.example.moviequotes.ui;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;

import com.example.moviequotes.R;
import com.example.moviequotes.base.BaseActivity;
import com.example.moviequotes.ui.ListQuoteFragment.ListQuoteFragment;

import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements ListQuoteFragment.OnListQuoteFragmentListener {

    ListQuoteFragment mListQuoteFragment;
    FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUnBinder(ButterKnife.bind(this));
        setFragment(getListQuoteFragment());
    }

    private void setFragment(Fragment fragment) {
        obtainFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment, fragment/*, fragment.getFragmentTag()*/)
                .commit();
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

    @Override
    public void onQueryClick(int id) {
        // TODO: 30.01.19 запускать второй фрагмент
    }
}
