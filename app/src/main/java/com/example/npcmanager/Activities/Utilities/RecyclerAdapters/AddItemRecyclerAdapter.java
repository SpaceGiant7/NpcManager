package com.example.npcmanager.Activities.Utilities.RecyclerAdapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.npcmanager.DataStructures.BaseItem;
import com.example.npcmanager.R;

import java.util.List;

public class AddItemRecyclerAdapter extends RecyclerView.Adapter<AddItemRecyclerAdapter.AddItemRecyclerViewHolder> {
    private Context context;
    private List<AddItemSelector> cardItems;

    public AddItemRecyclerAdapter(Context context,
            List<AddItemSelector> cardItems) {
        this.context = context;
        this.cardItems = cardItems;
    }

    @Override
    public AddItemRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        return new AddItemRecyclerViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.add_item_card_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AddItemRecyclerViewHolder holder, int position) {
        holder.addItemLabel.setText("Add " + cardItems.get(position).getLabel());
        holder.label.setText(cardItems.get(position).getLabel());
        setupContents(holder, position);
        holder.setSelectedItem(cardItems.get(position).getSelectedItem());
        holder.bindOnClick();
        holder.bindOnLongClick();
    }

    void setupContents(@NonNull AddItemRecyclerViewHolder holder, int position) {
        ArrayAdapter<BaseItem> adapter = new ArrayAdapter<>(
                context,
                android.R.layout.simple_spinner_item,
                cardItems.get(position).getBaseItems());
        holder.contents.setAdapter(adapter);
        holder.contents.setSelection(adapter.getPosition(
                cardItems.get(position).getSelectedItem()));
    }


    @Override
    public int getItemCount() {
        return cardItems.size();
    }


    static class AddItemRecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView addItemLabel;
        TextView label;
        Spinner contents;

        AddItemRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            addItemLabel = itemView.findViewById(R.id.addItemCardEnableLabel);
            label = itemView.findViewById(R.id.addItemCardLabel);
            contents = itemView.findViewById(R.id.addItemCardContents);
        }

        void setSelectedItem(BaseItem item) {
            if (item.isNone()) {
                disableCard();
            } else {
                enableCard();
            }
        }

        void bindOnClick() {
            enableCard();
        }

        void bindOnLongClick() {
            disableCard();
        }

        void enableCard() {
            addItemLabel.setVisibility(View.INVISIBLE);
            label.setVisibility(View.VISIBLE);
            contents.setVisibility(View.VISIBLE);
        }

        void disableCard() {
            addItemLabel.setVisibility(View.VISIBLE);
            label.setVisibility(View.INVISIBLE);
            contents.setVisibility(View.INVISIBLE);
        }

    }
}
