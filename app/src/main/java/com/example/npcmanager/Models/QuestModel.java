package com.example.npcmanager.Models;

import com.example.npcmanager.DataStructures.Quest;

import java.util.List;
import java.util.Optional;

public class QuestModel extends BaseModel<Quest> {
    public QuestModel() {
        super();
    }

    public void addQuest( Quest newQuest) {
        addItem(newQuest);
    }

    public Optional<Quest> getQuest(String questName ) {
        return list.stream()
                .filter( t -> t.getQuestName()
                        .equals( questName ) )
                .findFirst();
    }

    public List<Quest> getAllQuests() {
        return getList();
    }

    public int getNumberOfQuests() {
        return getNumberOfItems();
    }

}
