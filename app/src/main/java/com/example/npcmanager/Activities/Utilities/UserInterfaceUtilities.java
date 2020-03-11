package com.example.npcmanager.Activities.Utilities;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.npcmanager.DataStructures.BaseItem;
import com.example.npcmanager.R;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class UserInterfaceUtilities {

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

    public static void setupSelector(
            Spinner selector,
            List<BaseItem> items,
            BaseItem item,
            Context context) {
        ArrayAdapter<BaseItem> adapter = createSpinnerAdapter(items, context);
        selector.setAdapter(adapter);
        selector.setSelection(adapter.getPosition(item));
    }

    public static void setupSelector(
            Spinner selector,
            List<BaseItem> items,
            BaseItem item,
            Context context,
            Consumer<BaseItem> itemConsumer,
            Runnable onNoSelection) {
        ArrayAdapter<BaseItem> adapter = createSpinnerAdapter(items, context);
        selector.setAdapter(adapter);
        selector.setSelection(adapter.getPosition(item));
        selector.setOnItemSelectedListener(
                new DeselectableSelectorListener(itemConsumer, onNoSelection));
    }

    public static void setupSelector(
            Spinner selector,
            List<BaseItem> items,
            Context context,
            Consumer<BaseItem> itemConsumer,
            Runnable onNoSelection) {
        ArrayAdapter<BaseItem> adapter = createSpinnerAdapter(items, context);
        selector.setAdapter(adapter);
        selector.setOnItemSelectedListener(
                new DeselectableSelectorListener(itemConsumer, onNoSelection));
    }

    private static ArrayAdapter<BaseItem> createSpinnerAdapter(List<BaseItem> items, Context context) {
        return new DeselectableArrayAdapter(context, items);
    }

    public static BaseItem getDeselectableSelection(Spinner selector, Supplier<BaseItem> noneSupplier) {
        SpinnerAdapter adapter = selector.getAdapter();
        if (selector.getSelectedItemPosition() <= 0) {
            return noneSupplier.get();
        }
        return (BaseItem) adapter.getItem(selector.getSelectedItemPosition() - 1);
    }
}
