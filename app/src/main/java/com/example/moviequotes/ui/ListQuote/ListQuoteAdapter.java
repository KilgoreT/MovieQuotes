package com.example.moviequotes.ui.ListQuote;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.moviequotes.R;
import com.example.moviequotes.repository.entity.QuoteItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListQuoteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_PRELODER = 1;
    private static final int TYPE_ITEM_LEFT = 2;
    private static final int TYPE_ITEM_RIGHT = 3;
    private List<QuoteItem> mData = new ArrayList<>();
    private boolean showPreloader = false;
    private Listener listener;

    public void setData(List<QuoteItem> data) {
        this.mData = data == null ? Collections.<QuoteItem>emptyList() : data;
        notifyDataSetChanged();
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        switch (viewType) {
            case TYPE_PRELODER:
                return new PreloaderViewHolder(inflater.inflate(R.layout.item_preloader, viewGroup, false));
            case TYPE_ITEM_LEFT:
                View viewLeft = inflater.inflate(R.layout.item_quote_left, viewGroup, false);
                final QuoteLeftViewHolder holderLeft = new QuoteLeftViewHolder(viewLeft);
                viewLeft.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener != null) {
                            int pos = holderLeft.getAdapterPosition();
                            if (pos != RecyclerView.NO_POSITION) {
                                listener.onItemClick(mData.get(pos).getId());
                            }
                        }
                    }
                });
                return holderLeft;
            case TYPE_ITEM_RIGHT:
                View viewRight = inflater.inflate(R.layout.item_quote_right, viewGroup, false);
                final QuoteRightViewHolder holderRight = new QuoteRightViewHolder(viewRight);
                viewRight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener != null) {
                            int pos = holderRight.getAdapterPosition();
                            if (pos != RecyclerView.NO_POSITION) {
                                listener.onItemClick(mData.get(pos).getId());
                            }
                        }
                    }
                });
                return holderRight;

        }

        throw new IllegalArgumentException("Unknown viewType = " + viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()) {
            case TYPE_PRELODER:
                break;
            case TYPE_ITEM_LEFT:
                QuoteLeftViewHolder holderLeft = (QuoteLeftViewHolder) viewHolder;
                holderLeft.bind(mData.get(position));
                break;
            case TYPE_ITEM_RIGHT:
                QuoteRightViewHolder holderRight = (QuoteRightViewHolder) viewHolder;
                holderRight.bind(mData.get(position));
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position >= mData.size()) {
            return TYPE_PRELODER;
        }
        if (mData.get(position).getCreatedBy() == 0) {
            return TYPE_ITEM_LEFT;
        }
        return TYPE_ITEM_RIGHT;
    }

    @Override
    public int getItemCount() {
        return showPreloader ? mData.size() + 1 : mData.size();
    }

    public void showPreload(boolean show) {
        if (showPreloader != show) {
            if (showPreloader = show) {
                notifyDataSetChanged();
            }
        }
    }

    public interface Listener {
        void onItemClick(int id);
    }
}

class QuoteLeftViewHolder extends RecyclerView.ViewHolder {

    private TextView quote;

    public QuoteLeftViewHolder(@NonNull View itemView) {
        super(itemView);
        quote = itemView.findViewById(R.id.text_quote);
    }

    void bind(QuoteItem item) {
        quote.setText(item.getText());
    }
}

class QuoteRightViewHolder extends RecyclerView.ViewHolder {

    private TextView quote;

    public QuoteRightViewHolder(@NonNull View itemView) {
        super(itemView);
        quote = itemView.findViewById(R.id.text_quote);
    }

    void bind(QuoteItem item) {
        quote.setText(item.getText());
    }
}

class PreloaderViewHolder extends RecyclerView.ViewHolder {

    public PreloaderViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}
