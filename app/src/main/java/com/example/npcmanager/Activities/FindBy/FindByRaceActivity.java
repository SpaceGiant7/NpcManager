package com.example.npcmanager.Activities.FindBy;

import com.example.npcmanager.Activities.Utilities.ActivityUtilities;
import com.example.npcmanager.DataStructures.Person;
import com.example.npcmanager.DataStructures.Race;
import com.example.npcmanager.Models.ApplicationModels;

import java.util.List;

public class FindByRaceActivity extends FindByActivity {

    Race race;

   protected String deserializeInputs() {
        race = ActivityUtilities.getRaceExtraMaybe(getIntent()).get();
        return race.getName();
   }

   protected List<Person> getPersonList() {
       return ApplicationModels.getPersonModel().findPeople(race);
   }

   protected String getTitleString() {
       return "Race";
   }


}
