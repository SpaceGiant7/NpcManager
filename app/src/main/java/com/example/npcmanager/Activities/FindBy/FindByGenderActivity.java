package com.example.npcmanager.Activities.FindBy;

import com.example.npcmanager.Activities.Utilities.ActivityUtilities;
import com.example.npcmanager.DataStructures.Gender;
import com.example.npcmanager.DataStructures.Person;
import com.example.npcmanager.Models.ApplicationModels;

import java.util.List;

public class FindByGenderActivity extends FindByActivity {
    Gender gender;

    protected String deserializeInputs() {
        gender = (Gender) ActivityUtilities.getGenderExtraMaybe(getIntent()).get();
        return gender.getName();
    }

    protected List<Person> getPersonList() {
        return ApplicationModels.getPersonModel().findPeople(gender);
    }

    protected String getTitleString() {
        return "Gender";
    }
}
