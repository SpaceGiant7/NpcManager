package com.example.npcmanager.DataStructures;

public class Campaign implements BaseItem {
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
}
