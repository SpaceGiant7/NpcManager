package com.example.npcmanager.DataStructures;

import com.example.npcmanager.Constants.NpcConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Mortality implements BaseItem {
    ALIVE("Alive"),
    DEAD("Dead"),
    UNKNOWN("Unknown"),
    IMMORTAL("Immortal"),
    NONE(NpcConstants.NONE);

    private String state;

    Mortality(String state) {
        this.state = state;
    }

    public static List<BaseItem> getList() {
        List<BaseItem> list = new ArrayList<>(Arrays.asList(Mortality.values()));
        list.remove(Mortality.None());
        return list;
    }

    public static Mortality None() {
        return Mortality.NONE;
    }

    @Override
    public String getIdentifier() {
        return state;
    }

    @Override
    public boolean isNone() {
        return this.equals(Mortality.None());
    }

    public String toString() {
        return state;
    }

}
