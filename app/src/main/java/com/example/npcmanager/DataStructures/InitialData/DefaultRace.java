package com.example.npcmanager.DataStructures.InitialData;

public enum DefaultRace {
    DRAGONBORN( "Dragonborn", "Dragonborn look very much like dragons standing"
            + "erect in humanoid form, though they lack wings or a tail." ),
    DWARF( "Dwarf", "Bold and hardy, dwarves are known as skilled warriors, "
            + "miners, and workers of stone and metal." ),
    ELF( "Elf", "Elves are a magical people of otherworldly grace, living "
            + "in the world but not entirely part of it." ),
    FIRBOLG( "Firbolg", "Firbolg tribes cloister in remote forest strongholds, "
            + "preferring to spend their days in the quiet harmony ith the woods" ),
    GNOME( "Gnome", "A gnome's energy and enthusiasm for living shines through "
            + "every inch of his or her tiny body." ),
    GOBLIN( "Goblin", "Goblins occupy an uneasy place in a dangerous world, "
            + "and they react by lashing out at any creatures they believe they can bully" ),
    HALFELF( "Half-Elf", "Half-Elves combine what some say are the best qualities " +
            "of their elf and human parents." ),
    HALFLING( "Halfling", "The diminutive halflings survive in a world full "
            + "of larger creatures and avoiding notice or, barring that, avoiding offense." ),
    HALFORC( "Half-Orc", "Half-Orcs' grayish pigmentation, sloping foreheads, "
            + "jutting jaws, prominent teeth, and towering builds make their orcish heritage "
            + "plain for all to see"),
    HUMAN( "Human", "Humans are the most adaptable and ambitious people among "
            + "the common races. Whatever drives them, humans are the innovators, the achievers, "
            + "and the pioneers of the world."),
    ORC( "Orc", "Orcs live a life that has no place for weakness, and every "
            + "warrior must be strong enough to take what is needed by force " ),
    TIEFLING( "Tiefling", "To be greeted with stares and whispers, to suffer "
            + "violence and insult on the street, to see mistrust and fear in every eye: this "
            + "is the lot of the tiefling" );

    private final String name;
    private final String description;

    DefaultRace(String name, String description ) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return name;
    }
}
