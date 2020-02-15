package com.example.npcmanager.DataStorage;

import com.example.npcmanager.DataStructures.Location;
import com.example.npcmanager.DataStructures.Organization;
import com.example.npcmanager.DataStructures.Person;
import com.example.npcmanager.DataStructures.Quest;
import com.example.npcmanager.Models.ApplicationModels;
import com.example.npcmanager.Models.BaseModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.util.*;
import java.util.logging.Level;

public class NpcJsonReader {

    private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger( BaseModel.class.getName() );

    String path;

    public NpcJsonReader(String campaign ) {
        path = "/home/andrew/IdeaProjects/NPCManager/Saves/" + campaign + "/";
    }

    public void read() {
        if ( noSaveFileExists() ){
            LOGGER.log(Level.WARNING, String.format( "No save file exists in: %s", path ) );
            return;
        }
        String filepath = path + getMostRecentFile();
        try ( Reader reader = new FileReader( filepath ) ) {
            JsonReader jReader = new JsonReader( reader );
            jReader.setLenient( true );
            Gson gson = new GsonBuilder().create();
            ApplicationModels.setLocationList(
                    gson.fromJson( jReader, new TypeToken<List<Location>>(){}.getType() ) );
            ApplicationModels.setOrganizationList(
                    gson.fromJson( jReader, new TypeToken<List<Organization>>(){}.getType() ) );
            ApplicationModels.setPersonList( DataConverter.convertStorageToPeople(
                    gson.fromJson( jReader, new TypeToken<List<PersonStorage>>(){}.getType() ) ) );
            ApplicationModels.setQuestList( DataConverter.convertStorageToQuest(
                    gson.fromJson( jReader, new TypeToken<List<QuestStorage>>(){}.getType() ) ) );
        }
        catch ( IOException ex ) {
            LOGGER.log( Level.SEVERE, "Failed to read from file" + ex );
        }

    }

    private Boolean noSaveFileExists() {
        File folder = new File( path );
        return folder.listFiles().length == 0;
    }

    private String getMostRecentFile() {
        File folder = new File( path );
        ArrayList<File> listOfFiles =
                new ArrayList<>( Arrays.asList( folder.listFiles() ) );
        Collections.sort( listOfFiles, new FileComparator() );
        return listOfFiles.get( listOfFiles.size() - 1 ).getName();
    }

    private class FileComparator implements Comparator<File> {
        @Override
        public int compare( File x, File y ) {
            return x.getName().compareTo( y.getName() );
        }
    }
}
