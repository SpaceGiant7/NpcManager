package com.example.npcmanager.Models;

import com.example.npcmanager.DataStructures.Location;
import com.example.npcmanager.DataStructures.Person;
import com.example.npcmanager.DataStructures.Quest;

import java.util.List;
import java.util.Optional;

public class QuestModel extends BaseModel{
    public QuestModel() {
        super();
    }

    public void addQuest( Quest newQuest) {
        addItem(newQuest);
    }

    public void removeQuestIfExists(String name) {
        findFirstQuestMaybe(name).ifPresent(q -> list.remove(q));
    }

    public Quest findFirstQuest(String name) {
        return findFirstQuestMaybe(name)
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Quest with name: %s does not exist", name)));
    }

    private Optional<Quest> findFirstQuestMaybe(String name) {
        return getAllQuests().stream()
                .filter(t -> t.getQuestName()
                        .equals(name))
                .findFirst();
    }

    public List<Quest> getAllQuests() {
        return getList();
    }

    public void replaceLocation(String oldLocationName, Location newLocation) {
        getAllQuests()
                .stream()
                .filter(q -> q.getReturnLocation().getName().equals(oldLocationName))
                .forEach(q -> q.setReturnLocation(newLocation));
    }

    public void replacePerson(String oldPersonName, Person newPerson) {
        getAllQuests()
                .stream()
                .filter(q -> q.getQuestGiver().getName().equals(oldPersonName))
                .forEach(q -> q.setQuestGiver(newPerson));
    }


}
