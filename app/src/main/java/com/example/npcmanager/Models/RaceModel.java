package com.example.npcmanager.Models;

import com.example.npcmanager.DataStructures.InitialData.DefaultRace;
import com.example.npcmanager.DataStructures.Race;

public class RaceModel extends BaseModel<Race> {

    public RaceModel() {
        loadDefaults();
    }
    protected Race getNone() {
        return Race.None();
    }

    private void loadDefaults() {
        for(DefaultRace race: DefaultRace.values()) {
            addItem(new Race(race.getName(), race.getDescription()));
        }
    }
}
