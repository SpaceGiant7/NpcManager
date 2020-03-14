package com.example.npcmanager.Activities.FindBy;

import com.example.npcmanager.Activities.Utilities.ActivityUtilities;
import com.example.npcmanager.DataStructures.Occupation;
import com.example.npcmanager.DataStructures.Person;
import com.example.npcmanager.Models.ApplicationModels;

import java.util.List;

public class FindByOccupationActivity extends FindByActivity {

    Occupation occupation;

    protected String deserializeInputs() {
        occupation = (Occupation) ActivityUtilities.getOccupationExtraMaybe(getIntent()).get();
        return occupation.getName();
    }

    protected List<Person> getPersonList() {
        return ApplicationModels.getPersonModel().findPeople(occupation);
    }

    protected String getTitleString() {
        return "Occupation";
    }
}
