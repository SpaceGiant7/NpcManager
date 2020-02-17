package com.example.npcmanager.Models;

import com.example.npcmanager.DataStructures.Quest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class QuestModel extends BaseModel<Quest> {
    public QuestModel() {
        super();
    }

    public void addQuest( Quest newQuest) {
        addItem(newQuest);
    }

    public Optional<Quest> getQuest(String questName) {
        return list.stream()
                .filter(t -> t.getQuestName()
                        .equals(questName))
                .findFirst();
    }

    public Quest findFirstQuest(String name) {
        String regex = String.format( ".*%s.*", name);
        return getAllQuests().stream()
                .filter(q -> q.getQuestName().matches(regex))
                .findFirst().orElseThrow(() -> new IllegalArgumentException(
                        String.format("Quest with name: %s does not exist", name)));
    }

    public List<Quest> getAllQuests() {
        return getList();
    }

    public int getNumberOfQuests() {
        return getNumberOfItems();
    }

}
