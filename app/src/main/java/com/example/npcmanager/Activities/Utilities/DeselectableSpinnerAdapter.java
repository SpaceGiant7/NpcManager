package com.example.npcmanager.Activities.Utilities;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.npcmanager.DataStructures.BaseItem;
import com.example.npcmanager.R;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class DeselectableSpinnerAdapter {
    private Supplier<BaseItem> noneSupplier;
    private Spinner spinner;

    private DeselectableSpinnerAdapter(
            Activity activity,
            int id,
            List<BaseItem> items,
            Consumer<BaseItem> itemConsumer,
            Runnable onNoSelection,
            Supplier<BaseItem> noneSupplier) {
        this.spinner = activity.findViewById(id);
        this.noneSupplier = noneSupplier;
        DeselectableArrayAdapter adapter = new DeselectableArrayAdapter(activity, items);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new DeselectableSelectorListener(itemConsumer, onNoSelection));
    }

    private DeselectableSpinnerAdapter(
            Activity activity,
            int id,
            List<BaseItem> items,
            BaseItem item,
            Consumer<BaseItem> itemConsumer,
            Runnable onNoSelection,
            Supplier<BaseItem> noneSupplier) {
        this.spinner = activity.findViewById(id);
        this.noneSupplier = noneSupplier;
        DeselectableArrayAdapter adapter = new DeselectableArrayAdapter(activity, items);
        spinner.setAdapter(adapter);
        spinner.setSelection(adapter.getPosition(item));
        spinner.setOnItemSelectedListener(new DeselectableSelectorListener(itemConsumer, onNoSelection));
    }

    public static DeselectableSpinnerAdapter create(
            Activity activity,
            int id,
            List<BaseItem> items,
            Consumer<BaseItem> itemConsumer,
            Runnable onNoSelection,
            Supplier<BaseItem> noneSupplier) {
        return new DeselectableSpinnerAdapter(activity, id, items, itemConsumer, onNoSelection, noneSupplier);
    }

    public static DeselectableSpinnerAdapter create(
            Activity activity,
            int id,
            List<BaseItem> items,
            Optional<BaseItem> selectedItemMaybe,
            Consumer<BaseItem> itemConsumer,
            Runnable onNoSelection,
            Supplier<BaseItem> noneSupplier) {
        return selectedItemMaybe.map(
                baseItem -> new DeselectableSpinnerAdapter(
                        activity, id, items, baseItem, itemConsumer, onNoSelection, noneSupplier))
                .orElseGet(
                        () -> new DeselectableSpinnerAdapter(
                                activity, id, items, itemConsumer, onNoSelection, noneSupplier));
    }

    public static DeselectableSpinnerAdapter create(
            Activity activity,
            int id,
            List<BaseItem> items,
            BaseItem selectedItem,
            Consumer<BaseItem> itemConsumer,
            Runnable onNoSelection,
            Supplier<BaseItem> noneSupplier) {
        return new DeselectableSpinnerAdapter(
                activity, id, items, selectedItem, itemConsumer, onNoSelection, noneSupplier);
    }

    public static DeselectableSpinnerAdapter create(
            Activity activity,
            int id,
            List<BaseItem> items,
            BaseItem selectedItem,
            Supplier<BaseItem> noneSupplier) {
        return new DeselectableSpinnerAdapter(
                activity, id, items, selectedItem, item -> {}, () -> {}, noneSupplier);
    }

    public void setSelection(int i) {
        spinner.setSelection(i);
    }

    public void setSelection(BaseItem item) {
        spinner.setSelection(getAdapter().getPosition(item) + 1);
    }

    private DeselectableArrayAdapter getAdapter() {
        return (DeselectableArrayAdapter) spinner.getAdapter();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        spinner.setOnClickListener(listener);
    }

    public void setOnLongClickListener(View.OnLongClickListener listener) {
        spinner.setOnLongClickListener(listener);
    }

    public void setVisibility(int visibility) {
        spinner.setVisibility(visibility);
    }

    public BaseItem getSelectedItem() {

        if (spinner.getSelectedItemPosition() <= 0) {
            return noneSupplier.get();
        }
        return (BaseItem) spinner.getAdapter().getItem(spinner.getSelectedItemPosition() - 1);
    }

    private static class DeselectableArrayAdapter extends ArrayAdapter<BaseItem> {
        List<BaseItem> items;
        public DeselectableArrayAdapter(Context context, List<BaseItem> items) {
            super(context, android.R.layout.simple_spinner_item, items);
            this.items = items;
        }

        @Override
        public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
            if (position == 0) {
                return initialSelection();
            }
            return super.getDropDownView(position - 1, convertView, parent);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, @NonNull ViewGroup parent) {
            if (position == 0) {
                return initialSelection();
            }
            return super.getView(position - 1, convertView, parent);
        }

        @Override
        public int getCount() {
            return super.getCount() + 1; // Adjust for initial selection item
        }

        private View initialSelection() {
            // Just an example using a simple TextView. Create whatever default view
            // to suit your needs, inflating a separate layout if it's cleaner.
            TextView view = new TextView(getContext());
            int spacing = getContext().getResources().getDimensionPixelSize(R.dimen.spinnerSpacing);
            view.setPadding(spacing, 0, 0, 0);
            view.setText("--No Selection--");
            return view;
        }

    }

    private static class DeselectableSelectorListener implements AdapterView.OnItemSelectedListener{

        private Consumer<BaseItem> itemConsumer;
        private Runnable noItemSelectedRunnable;

        public DeselectableSelectorListener(Consumer<BaseItem> itemConsumer, Runnable noItemSelectedRunnable) {
            this.itemConsumer = itemConsumer;
            this.noItemSelectedRunnable = noItemSelectedRunnable;
        }

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            if (i == 0) {
                noItemSelectedRunnable.run();
                return;
            }
            i -= 1;
            itemConsumer.accept((BaseItem) adapterView.getAdapter().getItem(i));
        }


        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            noItemSelectedRunnable.run();
        }
    }
}
