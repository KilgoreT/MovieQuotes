package com.example.moviequotes.ui.ListQuoteFragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
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
    private static final int TYPE_ITEM = 2;
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
            case TYPE_ITEM:
                View view = inflater.inflate(R.layout.item_quote, viewGroup, false);
                final QuoteViewHolder holder = new QuoteViewHolder(view);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener != null) {
                            int pos = holder.getAdapterPosition();
                            if (pos != RecyclerView.NO_POSITION) {
                                listener.onItemClick(mData.get(pos).getId());
                            }
                        }
                    }
                });
                return holder;
        }

        throw new IllegalArgumentException("Unknown viewType=" + viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()) {
            case TYPE_PRELODER:
                Log.d("###", "bing preloader!!!!!!!");
                break;
            case TYPE_ITEM:
                QuoteViewHolder holder = (QuoteViewHolder) viewHolder;
                holder.bind(mData.get(position));
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position >= mData.size()) {
            return TYPE_PRELODER;
        }
        return TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return showPreloader ? mData.size() + 1 : mData.size();
//        return mData.size();
    }

    public void showPreload(boolean show) {
        showPreloader = show;
        if (show) {
//            notifyItemInserted(mData.size());
        } else {
//            notifyItemRemoved(mData.size());
        }
        notifyDataSetChanged();
//        notifyDataSetChanged();
    }

    public interface Listener {
        void onItemClick(int id);
    }
}

class QuoteViewHolder extends RecyclerView.ViewHolder {

    private TextView quote;

    public QuoteViewHolder(@NonNull View itemView) {
        super(itemView);
        quote = itemView.findViewById(R.id.text_quote);
    }

    void bind(QuoteItem item) {
        if (item.getId() == 0) {
//            quote.setGravity(Gravity.START);
//            quote.setTextColor(quote.getResources().getColor(R.color.colorPrimary));
//            quote.setPadding(48, 0, 0, 0);
        } else {
//            quote.setGravity(Gravity.END);
//            quote.setTextColor(quote.getResources().getColor(R.color.colorAccent));
//            quote.setPadding(0, 0, 48, 0);
        }
        quote.setText(item.getText());
    }
}

class PreloaderViewHolder extends RecyclerView.ViewHolder {

    public PreloaderViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}
