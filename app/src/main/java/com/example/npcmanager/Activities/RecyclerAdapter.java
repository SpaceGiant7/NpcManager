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

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {
    List<BaseItem> cardItems;

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.cardText);
        }
    }

    public RecyclerAdapter(List<BaseItem> cardList) {
        this.cardItems = cardList;

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
    }

    @Override
    public int getItemCount() {
        return cardItems.size();
    }

}
