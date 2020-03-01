package com.example.npcmanager.Models;

import com.example.npcmanager.DataStructures.InitialData.DefaultOccupation;
import com.example.npcmanager.DataStructures.Occupation;

public class OccupationModel extends BaseModel<Occupation> {

    public OccupationModel() {
        loadDefaults();
    }
    protected Occupation getNone() {
        return Occupation.None();
    }

    private void loadDefaults() {
        for(DefaultOccupation occupation: DefaultOccupation.values()) {
            addItem(new Occupation(occupation.getName(), occupation.getDescription()));
        }
    }
}
