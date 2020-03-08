package com.example.npcmanager.Activities.Utilities.RecyclerAdapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.npcmanager.DataStructures.ViewItem;
import com.example.npcmanager.R;

import java.util.List;

public class ViewItemRecyclerAdapter extends RecyclerView.Adapter<ViewItemRecyclerAdapter.ViewItemRecyclerViewHolder> {
    private List<ViewItem> cardItems;

    public ViewItemRecyclerAdapter(
            List<ViewItem> cardItems) {
        this.cardItems = cardItems;
    }

    @Override
    public ViewItemRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        return new ViewItemRecyclerViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.view_item_card_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewItemRecyclerViewHolder holder, int position) {
        holder.label.setText(cardItems.get(position).getLabel());
        holder.contents.setText(cardItems.get(position).getContents());
        holder.bindOnClick(cardItems.get(position));
    }


    @Override
    public int getItemCount() {
        return cardItems.size();
    }


    static class ViewItemRecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView label;
        TextView contents;

        ViewItemRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            label = itemView.findViewById(R.id.viewItemCardLabel);
            contents = itemView.findViewById(R.id.viewItemCardContents);
        }

        void bindOnClick(final ViewItem item) {
            itemView.setOnClickListener(v -> onItemClick(item));
        }

        private void onItemClick(final ViewItem item) {
            item.interact();
        }

    }
}
