package com.example.npcmanager.Models;

import com.example.npcmanager.DataStructures.Gender;
import com.example.npcmanager.DataStructures.InitialData.DefaultGender;

public class GenderModel extends BaseModel<Gender> {

    public GenderModel() {
        loadDefaults();
    }

    protected Gender getNone() {
        return Gender.None();
    }

    private void loadDefaults() {
        for (DefaultGender gender: DefaultGender.values()) {
            addItem(Gender.of(gender.getName()));
        }
    }
}
