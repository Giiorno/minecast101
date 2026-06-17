package com.example.examplemod.spells;

import net.minecraft.world.entity.player.Player;

public class SpellCast {

    private final Player caster;
    private final Spell spell;
    private final SpellSchool school;
    private final float outgoingDamage;
    private final float pierce;
    private final int spentPips;

    public SpellCast(
            Player caster,
            Spell spell,
            float outgoingDamage,
            float pierce,
            int spentPips
    ) {
        this.caster = caster;
        this.spell = spell;
        this.school = spell.getSchool();
        this.outgoingDamage = outgoingDamage;
        this.pierce = pierce;
        this.spentPips = spentPips;
    }

    public Player getCaster() {
        return caster;
    }

    public Spell getSpell() {
        return spell;
    }

    public SpellSchool getSchool() {
        return school;
    }

    public float getOutgoingDamage() {
        return outgoingDamage;
    }

    public float getPierce() {
        return pierce;
    }

    public int getSpentPips() {
        return spentPips;
    }
}