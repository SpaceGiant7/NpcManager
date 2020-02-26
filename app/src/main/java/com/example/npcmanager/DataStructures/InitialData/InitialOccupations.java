package com.example.npcmanager.DataStructures.InitialData;

import java.util.HashMap;
import java.util.Map;

public enum InitialOccupations {
    BARBER( "Barber", "Cuts hair" ),
    BARTENDER( "Bartender", "Makes drinks at the local bar" ),
    BLACKSMITH( "Blacksmith", "Works with metal to make weapons and armor" ),
    FARMER( "Farmer", "Farms things" ),
    MAYOR( "Mayor", "Runs the town" ),
    MERCENARY( "Mercenary", "Muscle for hire" ),
    MERCHANT( "Merchant", "Sells general goods" ),
    MONK( "Monk", "Practices self deprecation, makes beer too" ),
    NONE( "None", "Doesn't work" ),
    PRIEST( "Priest", "Works in the church" ),
    TANNER( "Tanner", "Works with leather" );

    private static final Map<String, InitialOccupations> BY_NAME = new HashMap<>();

    static {
        for (InitialOccupations o : values()) {
            BY_NAME.put(o.name, o);
        }
    }

    private final String name;
    private final String description;

    InitialOccupations( String name, String description ) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return name;
    }

    public static InitialOccupations fromName(String name) {
        return BY_NAME.get(name);
    }
}
