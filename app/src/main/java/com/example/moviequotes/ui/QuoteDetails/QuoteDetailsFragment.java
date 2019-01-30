package com.example.moviequotes.ui.QuoteDetails;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.chip.Chip;
import android.support.design.chip.ChipGroup;
import android.support.design.chip.ChipDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.moviequotes.App;
import com.example.moviequotes.R;
import com.example.moviequotes.base.BaseFragment;
import com.example.moviequotes.repository.entity.QuoteDetailsItem;
import com.example.moviequotes.repository.entity.TagItem;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static java.security.AccessController.getContext;


public class QuoteDetailsFragment extends BaseFragment implements QuoteDetailsMvpView {

    // TODO: 1/30/19 сюда нужно lce
    @Inject
    QuoteDetailsMvpPresenter<QuoteDetailsMvpView> mPresenter;

    @BindView(R.id.lce)
    public ProgressBar lce;

    @BindView(R.id.tag_group)
    public ChipGroup tagGroup;


    @BindView(R.id.text_date)
    public TextView tvDate;

    @BindView(R.id.text_quote)
    public TextView tvQuote;

    private int mParamId;

    public QuoteDetailsFragment() {
        // Required empty public constructor
    }

    public static QuoteDetailsFragment newInstance() {
        QuoteDetailsFragment fragment = new QuoteDetailsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_quote_detail, container, false);
        tvDate = view.findViewById(R.id.text_date);
        tvQuote = view.findViewById(R.id.text_quote);
        App.getInstance().getComponent().inject(this);
        setUnBinder(ButterKnife.bind(this, view));
        mPresenter.onAttach(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.onAttach(this);
    }

    @Override
    protected void startRequest() {
        lce.setVisibility(View.VISIBLE);
        tagGroup.setVisibility(View.GONE);
        tvDate.setVisibility(View.GONE);
        tvQuote.setVisibility(View.GONE);
        mPresenter.messageDetaild(mParamId);
    }

  /*  @Override
    public void onResume() {
        super.onResume();
        mPresenter.messageDetaild(mParamId);
    }*/

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public String getFragmentTag() {
        return null;
    }

    @Override
    public void onReceiveResult(QuoteDetailsItem item) {
        lce.setVisibility(View.GONE);
        tagGroup.setVisibility(View.VISIBLE);
        tvDate.setVisibility(View.VISIBLE);
        tvQuote.setVisibility(View.VISIBLE);
        tvDate.setText(item.getCreatedAt());
        tvQuote.setText(item.getText());
        for (TagItem tag: item.getTagList()) {
            Chip chip = new Chip(getContext());
            chip.setEnabled(false);
            chip.setText(tag.getLabel());
            ChipDrawable d = (ChipDrawable) chip.getChipDrawable();
            if (tag.getColor() != null) {
                d.setChipBackgroundColor(getState(tag.getColor()));
            } else {
                d.setChipBackgroundColor(getState("#BDBDBD"));
            }
            chip.setTextColor(Color.BLACK);

//            ChipDrawable chipDrawable = ChipDrawable.createFromAttributes(getContext(), )
            tagGroup.addView(chip);
        }
    }

    private ColorStateList getState(String color) {
        return new ColorStateList(
                new int[][]{
                        new int[]{android.R.attr.state_accelerated}
                },
                new int[] {
                        Color.parseColor(color)
                }
        );
    }

    @Override
    public void onReceiveError(Throwable throwable) {
        showSnackBar(throwable.getMessage());
    }

    public void setParamId(int paramId) {
        this.mParamId = paramId;
    }
}
