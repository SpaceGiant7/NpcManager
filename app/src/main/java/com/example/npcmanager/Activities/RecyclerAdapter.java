package com.example.npcmanager.Activities;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.npcmanager.DataStructures.BaseItem;
import com.example.npcmanager.R;

import java.util.List;
import java.util.function.Consumer;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {
    private List<BaseItem> cardItems;
    private Consumer<BaseItem> itemConsumer;

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.cardText);
        }
        public void bind(final BaseItem item, final Consumer<BaseItem> consumer) {
            itemView.setOnClickListener(v -> consumer.accept(item));
        }
    }

    public RecyclerAdapter(List<BaseItem> cardList, Consumer<BaseItem> itemConsumer) {
        this.cardItems = cardList;
        this.itemConsumer = itemConsumer;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        return new RecyclerViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.card_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.textView.setText(cardItems.get(position).getIdentifier());
        holder.bind(cardItems.get(position), itemConsumer);
    }

    @Override
    public int getItemCount() {
        return cardItems.size();
    }

}
