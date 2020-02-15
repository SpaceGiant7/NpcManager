package com.example.npcmanager.DataStorage;

import com.example.npcmanager.DataStructures.Person;
import com.example.npcmanager.DataStructures.Quest;

import java.util.List;
import java.util.stream.Collectors;

public class DataConverter {

    public static List<Person> convertStorageToPeople( List<PersonStorage> peopleStorage ) {
        return peopleStorage.stream().map( ps -> new Person( ps ) )
                .collect( Collectors.toList() );
    }

    public static List<Quest> convertStorageToQuest( List<QuestStorage> questStorages ) {
        return questStorages.stream().map( qs -> new Quest( qs ) )
                .collect( Collectors.toList() );
    }

    public static List<PersonStorage> convertPeopleToStorage( List<Person> people ) {
        return people.stream().map( p -> new PersonStorage( p ) )
                .collect( Collectors.toList() );
    }

    public static List<QuestStorage> convertQuestToStorage( List<Quest> quest ) {
        return quest.stream().map( q -> new QuestStorage( q ) )
                .collect( Collectors.toList() );
    }

}
