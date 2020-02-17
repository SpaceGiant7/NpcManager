package com.example.npcmanager.DataStorage;

import android.content.Context;

import com.example.npcmanager.Models.ApplicationModels;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CampaignReaderWriter {

    public static CampaignReaderWriter INSTANCE = new CampaignReaderWriter();

    public static void save(String campaignName, Context context) {
        INSTANCE.saveCampaign(campaignName, context);
    }

    public static void load(String campaignName, Context context) {
        INSTANCE.loadCampaign(campaignName, context);
    }

    private boolean saveCampaign(String campaignName, Context context) {
        String filename = campaignName + ".json";
        String jsonString = createJsonString();
        try {
            FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
            fos.write(jsonString.getBytes());
            fos.close();
        } catch (IOException fileNotFound) {
            return false;
        }
        return true;
    }

    private boolean loadCampaign(String campaignName, Context context) {
        String filename = campaignName + ".json";

        try {
            BufferedReader bufferedReader =
                    new BufferedReader(
                            new InputStreamReader(
                                    context.openFileInput(filename)));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            parseJsonString(sb.toString());
        } catch( IOException ioException) {
            return false;
        }
        return true;
    }

    public static List<String> getExistingCampaigns(Context context) {
        List<File> fileList = Arrays.asList(context.getFilesDir().listFiles());
        return fileList.stream()
                .map(File::getName)
                .filter(n -> n.contains(".json"))
                .map(n -> n.split(".json")[0])
                .collect(Collectors.toList());
    }

    private static String createJsonString() {
        String jString = "";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        jString += gson.toJson(new ApplicationDataTransferObject());
        return jString;
    }

    private static void parseJsonString(String jsonString) {
        Gson gson = new GsonBuilder().create();
        ApplicationDataTransferObject transferObject =
                gson.fromJson(jsonString, ApplicationDataTransferObject.class);

        ApplicationModels.setLocationList(transferObject.getLocations());
        ApplicationModels.setOrganizationList(transferObject.getOrganizations());
        ApplicationModels.setPersonList(transferObject.getPeople());
        ApplicationModels.setQuestList(transferObject.getQuests());
    }



}
