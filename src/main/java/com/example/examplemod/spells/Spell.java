package com.example.examplemod.spells;

import net.minecraft.world.entity.player.Player;

public abstract class Spell {

    private final String name;
    private final SpellSchool school;
    private final int pipCost;
    private final float baseDamage;

    protected Spell(String name, SpellSchool school, int pipCost, float baseDamage) {
        this.name = name;
        this.school = school;
        this.pipCost = pipCost;
        this.baseDamage = baseDamage;
    }

    public String getName() {
        return name;
    }

    public SpellSchool getSchool() {
        return school;
    }

    public int getPipCost() {
        return pipCost;
    }

    public float getBaseDamage(int spentPips) {
        return baseDamage;
    }

    public boolean canCast(int availablePips) {
        return availablePips >= pipCost;
    }

    public abstract void cast(Player player, int spentPips);
}