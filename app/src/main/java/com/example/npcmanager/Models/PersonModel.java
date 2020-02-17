package com.example.npcmanager.Models;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.npcmanager.DataStructures.Person;
import com.example.npcmanager.DataStructures.Location;
import com.example.npcmanager.DataStructures.Gender;
import com.example.npcmanager.DataStructures.Race;
import com.example.npcmanager.DataStructures.Occupation;
import com.example.npcmanager.DataStructures.Organization;

public class PersonModel extends BaseModel<Person> {
    public PersonModel() {
        super();
    }

    public void addPerson( Person newPerson ){
        addItem( newPerson );
    }

    public void removePersonIfExists(String name) {
        findFirstPersonMaybe(name).ifPresent(p -> list.remove(p));
    }

    public int getNumberOfPeople(){
        return getNumberOfItems();
    }

    public Person findFirstPerson( String name ) {
        return findFirstPersonMaybe(name)
                .orElseThrow( () -> new IllegalArgumentException(
                        String.format( "Person with name: %s does not exist", name ) ) );
    }

    private Optional<Person> findFirstPersonMaybe(String name) {
        return findPeople(name).stream().findFirst();
    }

    public Set<Person> findPeople( String name ) {
        String regex = String.format( ".*%s.*", name );
        return list.stream()
                .filter( p -> p.getName().matches( regex ) )
                .collect( Collectors.toSet() );
    }

    public Set<Person> findPeople( Race race ) {
        return list.stream()
                .filter( p -> p.getRace().equals( race ) )
                .collect( Collectors.toSet() );
    }

    public Set<Person> findPeople( Gender gender ) {
        return list.stream()
                .filter( p -> p.getGender().equals( gender ) )
                .collect( Collectors.toSet() );
    }

    public Set<Person> findPeople( Location location) {
        return list.stream()
                .filter( p -> p.getHome().equals(location) )
                .collect( Collectors.toSet() );
    }

    public Set<Person> findPeople( Occupation occupation ) {
        return list.stream()
                .filter( p -> p.getOccpation().equals( occupation ) )
                .collect( Collectors.toSet() );
    }

    public Set<Person> findPeople( Organization organization ) {
        return list.stream()
                .filter( p -> p.getOrganization().equals( organization ) )
                .collect( Collectors.toSet() );
    }

    public List<Person> getAllPeople(){
        return list;
    }

    public boolean personExists( Person person ) {
        return itemExists( person );
    }


}
