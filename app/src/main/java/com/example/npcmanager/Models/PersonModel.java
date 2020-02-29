package com.example.npcmanager.Models;

import com.example.npcmanager.DataStructures.Gender;
import com.example.npcmanager.DataStructures.Location;
import com.example.npcmanager.DataStructures.Occupation;
import com.example.npcmanager.DataStructures.Organization;
import com.example.npcmanager.DataStructures.Person;
import com.example.npcmanager.DataStructures.Race;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PersonModel extends BaseModel<Person>{
    public PersonModel() {
        super();
    }

    protected Person getNone() {
        return Person.None();
    }

    public Person findFirstPerson(String name) {
        return getItemMaybe(name)
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Person with name: %s does not exist", name)));
    }

    public Set<Person> findPeople(String name) {
        String regex = String.format(".*%s.*", name);
        return getAllItems().stream()
                .filter(p -> p.getName().matches(regex))
                .collect(Collectors.toSet());
    }

    public List<Person> findPeople(Race race) {
        return getAllItems().stream()
                .filter(p -> p.getRace().equals(race))
                .collect(Collectors.toList());
    }

    public List<Person> findPeople(Gender gender) {
        return getAllItems().stream()
                .filter(p -> p.getGender().equals(gender))
                .collect(Collectors.toList());
    }

    public List<Person> findPeople(Location location) {
        return getAllItems().stream()
                .filter(p -> p.getHome().equals(location))
                .collect(Collectors.toList());
    }

    public List<Person> findPeople(Occupation occupation) {
        return getAllItems().stream()
                .filter(p -> p.getOccpation().equals(occupation))
                .collect(Collectors.toList());
    }

    public List<Person> findPeople(Organization organization) {
        return getAllItems().stream()
                .filter(p -> p.getOrganization().equals(organization))
                .collect(Collectors.toList());
    }

    void replaceLocation(String oldLocationName, Location newLocation) {
        getAllItems()
                .stream()
                .filter(p -> p.getHome().getName().equals(oldLocationName))
                .forEach(p -> p.setHome(newLocation));
    }

    void replaceOrganization(String oldOrganizationName, Organization newOrganization) {
        getAllItems()
                .stream()
                .filter(p -> p.getOrganization().getName().equals(oldOrganizationName))
                .forEach(p -> p.setOrganization(newOrganization));
    }

    void replaceRace(String oldRaceName, Race newRace) {
        getAllItems()
                .stream()
                .filter(p -> p.getRace().getName().equals(oldRaceName))
                .forEach(p -> p.setRace(newRace));
    }

    void replaceOccupation(String oldOccupationName, Occupation newOccupation) {
        getAllItems()
                .stream()
                .filter(p -> p.getOccpation().getName().equals(oldOccupationName))
                .forEach(p -> p.setOccupation(newOccupation));
    }

    void replaceGender(String oldGenderName, Gender newGender) {
        getAllItems()
                .stream()
                .filter(p -> p.getGender().getName().equals(oldGenderName))
                .forEach(p -> p.setGender(newGender));
    }
}
