package com.example.npcmanager.Activities;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.npcmanager.DataStructures.Person;
import com.example.npcmanager.DataStructures.Quest;
import com.example.npcmanager.Models.ApplicationModels;

import java.util.ArrayList;
import java.util.function.Function;

class PersonListSelectorListener<T> implements AdapterView.OnItemSelectedListener {
    private Context context;
    private ListView personList;
    private Function<T, ArrayList<Person>> findPeopleFunction;

    PersonListSelectorListener(
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
                ApplicationModels.getPersonModel().getAllPeople());
        personList.setAdapter(occupationAdaptor);
    }
}

class PersonSelectorListener implements AdapterView.OnItemClickListener {
    private AppCompatActivity currentActivity;
    private Class<?> activityClassToOpen;

    public PersonSelectorListener(AppCompatActivity currentActivity, Class<?> activityClassToOpen) {
        this.currentActivity = currentActivity;
        this.activityClassToOpen = activityClassToOpen;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Person person = (Person) adapterView.getAdapter().getItem(i);
        Intent intent = new Intent( currentActivity, activityClassToOpen);
        intent.putExtra("name", person.getName());
        currentActivity.startActivity(intent);
    }
}

class QuestSelectorListener implements AdapterView.OnItemClickListener {

    private AppCompatActivity currentActivity;
    private Class<?> activityClassToOpen;

    public QuestSelectorListener(AppCompatActivity currentActivity, Class<?> activityClassToOpen) {
        this.currentActivity = currentActivity;
        this.activityClassToOpen = activityClassToOpen;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Quest quest = (Quest) adapterView.getAdapter().getItem(i);
        Intent intent = new Intent( currentActivity, activityClassToOpen);
        intent.putExtra("name", quest.getQuestName());
        currentActivity.startActivity(intent);
    }
}
