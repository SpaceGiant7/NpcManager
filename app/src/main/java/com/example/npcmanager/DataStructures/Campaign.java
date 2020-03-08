package com.example.npcmanager.DataStructures;

import java.io.Serializable;

public class Campaign implements BaseItem, Serializable {
    String name;

    public Campaign(String name) {
        this.name = name;
    }

    public static Campaign of(String name) {
        return new Campaign(name);
    }

    public String getIdentifier() {
        return name;
    }

    @Override
    public boolean isNone() {
        return false;
    }
}
