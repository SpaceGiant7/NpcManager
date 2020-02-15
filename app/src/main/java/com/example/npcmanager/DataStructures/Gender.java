package com.example.npcmanager.DataStructures;

public enum Gender {
    FEMALE( "Female" ),
    MALE( "Male" ),
    UNKNOWN( "Unknown" );

    private final String name;

    Gender( String name ){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
