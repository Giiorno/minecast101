package com.example.examplemod.spells;

import net.minecraft.world.entity.player.Player;

public abstract class Spell {

    private final String name;
    private final int pipCost;
    private final SpellSchool school;

    protected Spell(String name, int pipCost, SpellSchool school) {
        this.name = name;
        this.pipCost = pipCost;
        this.school = school;
    }

    public String getName() {
        return name;
    }

    public int getPipCost() {
        return pipCost;
    }

    public SpellSchool getSchool() {
        return school;
    }

    public abstract void cast(Player player);
}