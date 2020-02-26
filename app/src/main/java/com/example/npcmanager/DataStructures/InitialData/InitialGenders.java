package com.example.npcmanager.DataStructures.InitialData;

public enum InitialGenders {
    FEMALE( "Female" ),
    MALE( "Male" ),
    UNKNOWN( "Unknown" );

    private final String name;

    InitialGenders( String name ){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
