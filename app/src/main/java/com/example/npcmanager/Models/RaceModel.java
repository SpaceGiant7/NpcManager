package com.example.npcmanager.Models;

import com.example.npcmanager.DataStructures.Race;

public class RaceModel extends BaseModel<Race> {
    protected Race getNone() {
        return Race.None();
    }
}
