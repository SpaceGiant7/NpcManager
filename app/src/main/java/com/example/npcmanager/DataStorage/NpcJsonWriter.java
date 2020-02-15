package com.example.npcmanager.DataStorage;

import com.example.npcmanager.DataStructures.Location;
import com.example.npcmanager.DataStructures.Organization;
import com.example.npcmanager.Models.ApplicationModels;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NpcJsonWriter {

    String path;
    private Logger logger;

    public NpcJsonWriter(String campaign, Logger logger) {
        this.logger = logger;
        path = "/home/andrew/IdeaProjects/NPCManager/Saves/" + campaign + "/";
    }

    public void write() {
        MakePathDirectory();
        String filepath = path + getDateTime() + ".json";
        try ( Writer writer = new FileWriter( filepath ) ) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson( new ArrayList<Location>( ApplicationModels.getLocationModel().getList() ), writer );
            gson.toJson( new ArrayList<Organization>( ApplicationModels.getOrganizationModel().getList() ), writer );
            gson.toJson( new ArrayList<PersonStorage>( DataConverter.convertPeopleToStorage(
                    ApplicationModels.getPersonModel().getList() ) ), writer );
            gson.toJson( new ArrayList<QuestStorage>( DataConverter.convertQuestToStorage(
                    ApplicationModels.getQuestModel().getList() ) ), writer );

        }
        catch ( IOException ex ) {
            logger.log(Level.SEVERE, "Failed to write to file" + ex );
        }
    }

    private String getDateTime() {
        return LocalDateTime.now()
                .format( DateTimeFormatter.ofPattern( "YYYY_MM_dd_HH_mm" ) );
    }

    private void MakePathDirectory() {
        new File( path ).mkdirs();
    }
}
