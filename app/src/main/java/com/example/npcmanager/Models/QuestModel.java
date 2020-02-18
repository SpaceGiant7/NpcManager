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

    public int getNumberOfQuests() {
        return getNumberOfItems();
    }

}
