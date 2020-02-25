package com.example.npcmanager.Models;

import com.example.npcmanager.DataStructures.Location;
import com.example.npcmanager.DataStructures.Person;
import com.example.npcmanager.DataStructures.Quest;

public class QuestModel extends BaseModel<Quest>{
    public QuestModel() {
        super();
    }

    protected Quest getNone() {
        return Quest.None();
    }

    public Quest getQuest(String name) {
        return getItemMaybe(name)
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Quest with name: %s does not exist", name)));
    }

    public void replaceLocation(String oldLocationName, Location newLocation) {
        getAllItems()
                .stream()
                .filter(q -> q.getReturnLocation().getName().equals(oldLocationName))
                .forEach(q -> q.setReturnLocation(newLocation));
    }

    public void replacePerson(String oldPersonName, Person newPerson) {
        getAllItems()
                .stream()
                .filter(q -> q.getQuestGiver().getName().equals(oldPersonName))
                .forEach(q -> q.setQuestGiver(newPerson));
    }


}
