package com.example.npcmanager.Models;

import com.example.npcmanager.DataStructures.Gender;

public class GenderModel extends BaseModel<Gender> {
    protected Gender getNone() {
        return Gender.None();
    }
}
