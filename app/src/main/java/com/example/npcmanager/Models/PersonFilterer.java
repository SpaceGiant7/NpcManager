package com.example.npcmanager.Models;

import com.example.npcmanager.DataStructures.BaseItem;
import com.example.npcmanager.DataStructures.Person;
import com.example.npcmanager.DataStructures.PersonTrait;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PersonFilterer {

    private Map<PersonTrait, Predicate<Person>> filters = new HashMap<>();

    public void addNameFilter(String name) {
        filters.put(PersonTrait.NAME, p -> p.getName().toLowerCase().contains(name.toLowerCase()));
    }

    public void addRaceFilter(BaseItem race) {
        filters.put(PersonTrait.RACE, p -> p.getRace().equals(race));
    }

    public void addGenderFilter(BaseItem gender) {
        filters.put(PersonTrait.GENDER, p -> p.getGender().equals(gender));
    }

    public void addLocationFilter(BaseItem location) {
        filters.put(PersonTrait.LOCATION, p -> p.getHome().equals(location));
    }

    public void addOccupationFilter(BaseItem occupation) {
        filters.put(PersonTrait.OCCUPATION, p -> p.getOccpation().equals(occupation));
    }

    public void addOrganizationFilter(BaseItem organization) {
        filters.put(PersonTrait.ORGANIZATION, p -> p.getOrganization().equals(organization));
    }

    public void addMortalityFilter(BaseItem mortality) {
        filters.put(PersonTrait.MORTALITY, p -> p.getMortality().equals(mortality));
    }


    public void removeFilter(PersonTrait trait) {
        filters.remove(trait);
    }

    public List<Person> getPeople() {
        return ApplicationModels.getPersonModel().getAllItems().stream()
                .filter(combineFilters())
                .collect(Collectors.toList());
    }

    private Predicate<Person> combineFilters() {
        return filters.values().stream().reduce(p->true, Predicate::and);
    }
}
