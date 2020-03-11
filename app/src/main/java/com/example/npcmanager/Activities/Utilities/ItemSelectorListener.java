package com.example.npcmanager.Activities.Utilities;

import android.view.View;
import android.widget.AdapterView;

import com.example.npcmanager.DataStructures.BaseItem;

import java.util.function.Consumer;

public class ItemSelectorListener<T extends BaseItem> implements AdapterView.OnItemSelectedListener{

    private Consumer<T> itemConsumer;
    private Runnable noItemSelectedRunnable;

    public ItemSelectorListener(Consumer<T> itemConsumer, Runnable noItemSelectedRunnable) {
        this.itemConsumer = itemConsumer;
        this.noItemSelectedRunnable = noItemSelectedRunnable;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        T item = (T) adapterView.getAdapter().getItem(i);
        itemConsumer.accept(item);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        noItemSelectedRunnable.run();
    }
}
