package com.example.npcmanager.Models;

import com.example.npcmanager.DataStructures.Gender;
import com.example.npcmanager.DataStructures.Location;
import com.example.npcmanager.DataStructures.Occupation;
import com.example.npcmanager.DataStructures.Organization;
import com.example.npcmanager.DataStructures.Person;
import com.example.npcmanager.DataStructures.Race;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class PersonModel extends BaseModel{
    public PersonModel() {
        super();
        addPerson(Person.None());
    }

    public void addPerson(Person newPerson) {
        if (newPerson.getName().length() > 0) {
            addItem(newPerson);
        }
    }

    public void removePersonIfExists(String name) {
        findFirstPersonMaybe(name).ifPresent(this::removeItem);
    }

    public int getNumberOfPeople() {
        return getNumberOfItems();
    }

    public Person findFirstPerson(String name) {
        return findFirstPersonMaybe(name)
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Person with name: %s does not exist", name)));
    }

    private Optional<Person> findFirstPersonMaybe(String name) {
        return findPeople(name).stream().findFirst();
    }

    public Set<Person> findPeople(String name) {
        String regex = String.format(".*%s.*", name);
        return getAllPeople().stream()
                .filter(p -> p.getName().matches(regex))
                .collect(Collectors.toSet());
    }

    public Set<Person> findPeople(Race race) {
        return getAllPeople().stream()
                .filter(p -> p.getRace().equals(race))
                .collect(Collectors.toSet());
    }

    public Set<Person> findPeople(Gender gender) {
        return getAllPeople().stream()
                .filter(p -> p.getGender().equals(gender))
                .collect(Collectors.toSet());
    }

    public Set<Person> findPeople(Location location) {
        return getAllPeople().stream()
                .filter(p -> p.getHome().equals(location))
                .collect(Collectors.toSet());
    }

    public Set<Person> findPeople(Occupation occupation) {
        return getAllPeople().stream()
                .filter(p -> p.getOccpation().equals(occupation))
                .collect(Collectors.toSet());
    }

    public Set<Person> findPeople(Organization organization) {
        return getAllPeople().stream()
                .filter(p -> p.getOrganization().equals(organization))
                .collect(Collectors.toSet());
    }

    public List<Person> getAllPeople() {
        return getList();
    }

    public boolean personExists(String name) {
        return findFirstPersonMaybe(name).isPresent();
    }

    public void replaceLocation(String oldLocationName, Location newLocation) {
        getAllPeople()
                .stream()
                .filter(p -> p.getHome().getName().equals(oldLocationName))
                .forEach(p -> p.setHome(newLocation));
    }

    public void replaceOrganization(String oldOrganizationName, Organization newOrganization) {
        getAllPeople()
                .stream()
                .filter(p -> p.getOrganization().getName().equals(oldOrganizationName))
                .forEach(p -> p.setOrganization(newOrganization));
    }

}
