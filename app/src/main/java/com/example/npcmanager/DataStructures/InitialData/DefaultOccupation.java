package com.example.npcmanager.DataStructures.InitialData;

public enum DefaultOccupation {
    BARBER( "Barber", "Cuts hair" ),
    BARTENDER( "Bartender", "Makes drinks at the local bar" ),
    BLACKSMITH( "Blacksmith", "Works with metal to make weapons and armor" ),
    FARMER( "Farmer", "Farms things" ),
    MAYOR( "Mayor", "Runs the town" ),
    MERCENARY( "Mercenary", "Muscle for hire" ),
    MERCHANT( "Merchant", "Sells general goods" ),
    MONK( "Monk", "Practices self deprecation, makes beer too" ),
    PRIEST( "Priest", "Works in the church" ),
    TANNER( "Tanner", "Works with leather" );

    private final String name;
    private final String description;

    DefaultOccupation(String name, String description ) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return name;
    }
}
