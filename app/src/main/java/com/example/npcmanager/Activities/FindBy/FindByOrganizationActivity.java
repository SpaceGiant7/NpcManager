package com.example.npcmanager.Activities.FindBy;

import com.example.npcmanager.Activities.Utilities.ActivityUtilities;
import com.example.npcmanager.DataStructures.Organization;
import com.example.npcmanager.DataStructures.Person;
import com.example.npcmanager.Models.ApplicationModels;

import java.util.List;

public class FindByOrganizationActivity extends FindByActivity {

    Organization organization;

    protected String deserializeInputs() {
        organization = ActivityUtilities.getOrganizationExtraMaybe(getIntent()).get();
        return organization.getName();
    }

    protected List<Person> getPersonList() {
        return ApplicationModels.getPersonModel().findPeople(organization);
    }

    protected String getTitleString() {
        return "Organization";
    }


}


