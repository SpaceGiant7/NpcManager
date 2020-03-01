package com.example.npcmanager.DataStructures.InitialData;

public enum DefaultGender {
    FEMALE( "Female" ),
    MALE( "Male" );

    private final String name;

    DefaultGender(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
