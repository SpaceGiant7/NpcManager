package com.example.npcmanager.Activities.Utilities;


import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.npcmanager.DataStructures.BaseItem;
import com.example.npcmanager.R;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {
    private List<? extends BaseItem> cardItems;
    private Supplier<List<? extends BaseItem>> listSupplier;
    private Consumer<BaseItem> selectItemConsumer;
    private Consumer<BaseItem> deleteItemConsumer;
    private RecyclerViewHolderModel viewHolderModel;
    int selectedItem = RecyclerView.NO_POSITION;

    public RecyclerAdapter(
            Supplier<List<? extends BaseItem>> listSupplier,
            Consumer<BaseItem> selectItemConsumer,
            Consumer<BaseItem> deleteItemConsumer) {
        this.listSupplier = listSupplier;
        this.cardItems = listSupplier.get();
        this.selectItemConsumer = selectItemConsumer;
        this.deleteItemConsumer = deleteItemConsumer;
        viewHolderModel = new RecyclerViewHolderModel();
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        RecyclerViewHolder viewHolder =  new RecyclerViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.card_layout, parent, false), viewHolderModel);
        viewHolderModel.addItem(viewHolder);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.textView.setText(cardItems.get(position).getIdentifier());
        holder.bindOnClick(cardItems.get(position), selectItemConsumer);
        holder.bindOnLongClick(cardItems.get(position), deleteItemConsumer);
    }


    @Override
    public int getItemCount() {
        return cardItems.size();
    }

    public void reloadItems() {
        cardItems = listSupplier.get();
        viewHolderModel.clearSelection();
        notifyDataSetChanged();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageButton deleteButton;
        RecyclerViewHolderModel model;

        RecyclerViewHolder(@NonNull View itemView, RecyclerViewHolderModel model) {
            super(itemView);
            textView = itemView.findViewById(R.id.cardText);
            deleteButton = itemView.findViewById(R.id.cardDeleteButton);
            this.model = model;
            this.itemView.setBackgroundColor(Color.WHITE);
        }

        void bindOnClick(final BaseItem item, final Consumer<BaseItem> consumer) {
            itemView.setOnClickListener(v -> onItemClick(item, consumer));
        }

        void bindOnLongClick(final BaseItem item, final Consumer<BaseItem> consumer) {
            itemView.setOnLongClickListener(v -> onItemLongClick(item, consumer));
        }

        private void onItemClick(final BaseItem item, final Consumer<BaseItem> consumer) {
            consumer.accept(item);
            model.clearSelection();
            itemView.setBackgroundColor(Color.GREEN);
            deleteButton.setVisibility(View.INVISIBLE);
        }

        private boolean onItemLongClick(final BaseItem item, final Consumer<BaseItem> consumer) {
            model.clearSelection();
            deleteButton.setVisibility(View.VISIBLE);
            itemView.setBackgroundColor(Color.RED);
            deleteButton.setOnClickListener(v -> consumer.accept(item));
            return true;
        }

        void resetItem() {
            deleteButton.setVisibility(View.INVISIBLE);
            itemView.setBackgroundColor(Color.WHITE);
        }

    }

    private static class RecyclerViewHolderModel {
        List<RecyclerViewHolder> viewHolders = new ArrayList<>();
        int selectedIndex = RecyclerView.NO_POSITION;

        public void addItem(RecyclerViewHolder v) {
            viewHolders.add(v);
        }

        public void clearSelection() {
            for (RecyclerViewHolder v : viewHolders) {
                v.resetItem();
            }
        }
    }

}
