package com.example.npcmanager.Activities.Utilities;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.npcmanager.DataStructures.Person;
import com.example.npcmanager.Models.ApplicationModels;

import java.util.ArrayList;
import java.util.function.Function;

public class PersonListSelectorListener<T> implements AdapterView.OnItemSelectedListener {
    private Context context;
    private ListView personList;
    private Function<T, ArrayList<Person>> findPeopleFunction;

    public PersonListSelectorListener(
            Context context,
            ListView personList,
            Function<T, ArrayList<Person>> findPeopleFunction) {
        this.context = context;
        this.personList = personList;
        this.findPeopleFunction = findPeopleFunction;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        fillPersonList(adapterView);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        resetPersonList();
    }

    private void fillPersonList(AdapterView<?> view) {
        T item = (T) view.getSelectedItem();
        ArrayAdapter<Person> arrayAdapter = new ArrayAdapter<>(
                context, android.R.layout.simple_list_item_1,
                findPeopleFunction.apply(item));
        personList.setAdapter(arrayAdapter);
    }

    private void resetPersonList() {
        ArrayAdapter<Person> occupationAdaptor = new ArrayAdapter<>(
                context, android.R.layout.simple_list_item_1,
                ApplicationModels.getPersonModel().getAllItems());
        personList.setAdapter(occupationAdaptor);
    }
}
