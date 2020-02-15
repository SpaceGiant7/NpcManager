package com.example.npcmanager.DataStructures;

public enum Occupation {
    BARBER( "Barber", "Cuts hair" ),
    BARTENDER( "Bartender", "Makes drinks at the local bar" ),
    BLACKSMITH( "Blacksmith", "Works with metal to make weapons and armor" ),
    FARMER( "Farmer", "Farms things" ),
    MAYOR( "Mayor", "Runs the town" ),
    MERCENARY( "Mercenary", "Muscle for hire" ),
    MERCHANT( "Merchant", "Sells general goods" ),
    MONK( "Monk", "Practices self deprecation, makes beer too" ),
    NONE( "None", "Doesn't work" ),
    PREIST( "Preist", "Works in the church" ),
    TANNER( "Tanner", "Works with leather" );

    private final String name;
    private final String description;

    Occupation( String name, String description ) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return name;
    }
}
