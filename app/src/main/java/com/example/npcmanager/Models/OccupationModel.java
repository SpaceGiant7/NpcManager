package com.example.npcmanager.Models;

import com.example.npcmanager.DataStructures.Occupation;

public class OccupationModel extends BaseModel<Occupation> {
    protected Occupation getNone() {
        return Occupation.None();
    }
}
