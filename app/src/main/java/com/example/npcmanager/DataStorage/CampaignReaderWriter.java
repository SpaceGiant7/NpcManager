package com.example.npcmanager.DataStorage;

import android.content.Context;

import com.example.npcmanager.DataStructures.Campaign;
import com.example.npcmanager.Models.ApplicationModels;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CampaignReaderWriter {

    public static void save(String campaignName, Context context) {
        saveCampaign(campaignName, context);
    }

    public static void load(String campaignName, Context context) {
        loadCampaign(campaignName, context);
    }

    public static void delete(String campaignName, Context context) {
        String filename = campaignName + ".json";
        File[] files = context.getFilesDir().listFiles((dir, name) -> name.equals(filename));
        if (files.length > 0) {
            files[0].delete();
        }
    }

    public static void rename(String oldCampaignName, String newCampaignName, Context context) {
        String oldFileName = oldCampaignName + ".json";
        String newFileName = newCampaignName + ".json";
        File[] files = context.getFilesDir().listFiles(
                (dir, name) -> name.equals(oldFileName));
        if(files.length > 0) {
            File newFile = new File(context.getFilesDir(), newFileName);
            files[0].renameTo(newFile);
        }
    }

    public static List<Campaign> getExistingCampaigns(Context context) {
        List<File> fileList = Arrays.asList(context.getFilesDir().listFiles());
        return fileList.stream()
                .map(File::getName)
                .filter(n -> n.contains(".json"))
                .map(CampaignReaderWriter::splitFileName)
                .map(n -> Campaign.of(n))
                .collect(Collectors.toList());
    }

    private static String splitFileName(String fileName) {
        return fileName.replace(".json", "");
    }

    private static boolean saveCampaign(String campaignName, Context context) {
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

    private static boolean loadCampaign(String campaignName, Context context) {
        String filename = campaignName + ".json";

        try {
            BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(context.openFileInput(filename)));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            parseJsonString(sb.toString());
        } catch (IOException ioException) {
            return false;
        }
        return true;
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

        ApplicationModels.setPersonList(transferObject.getPeople());
        ApplicationModels.setQuestList(transferObject.getQuests());
        ApplicationModels.setOrganizationList(transferObject.getOrganizations());
        ApplicationModels.setLocationList(transferObject.getLocations());
        ApplicationModels.setRaceList(transferObject.getRaces());
        ApplicationModels.setOccupationList(transferObject.getOccupations());
        ApplicationModels.setGenderList(transferObject.getGenders());
    }
}
