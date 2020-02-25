package com.example.npcmanager.Activities.FindBy;

import com.example.npcmanager.Activities.Utilities.ActivityUtilities;
import com.example.npcmanager.DataStructures.Location;
import com.example.npcmanager.DataStructures.Person;
import com.example.npcmanager.Models.ApplicationModels;

import java.util.List;

public class FindByLocationActivity extends FindByActivity {

    Location location;

    protected String deserializeInputs() {
        location = ActivityUtilities.getLocationExtraMaybe(getIntent()).get();
        return location.getName();
    }

    protected List<Person> getPersonList() {
        return ApplicationModels.getPersonModel().findPeople(location);
    }

    protected String getTitleString() {
        return "Location";
    }

}


