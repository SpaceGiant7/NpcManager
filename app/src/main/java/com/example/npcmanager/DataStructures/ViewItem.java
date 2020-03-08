package com.example.npcmanager.DataStructures;


public class ViewItem {
    private String label;
    private String contents;
    private Runnable onInteraction;

    public ViewItem(String label, String contents) {
        this.label = label;
        this.contents = contents;
        onInteraction = () -> {};
    }

    public ViewItem(String label, String contents, Runnable onInteraction) {
        this.label = label;
        this.contents = contents;
        this.onInteraction = onInteraction;
    }

    public String getLabel() {
        return label;
    }

    public String getContents() {
        return contents;
    }

    public void interact() {
        onInteraction.run();
    }
}
