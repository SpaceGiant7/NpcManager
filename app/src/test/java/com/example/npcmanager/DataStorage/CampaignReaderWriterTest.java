package com.example.npcmanager.DataStorage;

import com.example.npcmanager.DataStructures.Location;
import com.example.npcmanager.Models.LocationModel;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CampaignReaderWriterTest {
    @Test
    public void test_location_to_json() {
        List<Location> locations = new ArrayList<>();
        locations.add(Location.of("Saskatoon"));
        locations.add(Location.of("WaterDeep"));
        CampaignReaderWriter readerWriter = new CampaignReaderWriter();

    }
}
