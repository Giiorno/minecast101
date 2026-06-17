package com.example.examplemod.combat;

import com.example.examplemod.spells.SpellSchool;

import java.util.EnumMap;

public class PlayerCombatStats {

    private SpellSchool primarySchool = SpellSchool.FIRE;

    private final EnumMap<SpellSchool, Float> damageBonus =
            new EnumMap<>(SpellSchool.class);

    private final EnumMap<SpellSchool, Float> resist =
            new EnumMap<>(SpellSchool.class);

    private final EnumMap<SpellSchool, Float> pierce =
            new EnumMap<>(SpellSchool.class);

    private float powerPipChance = 20.0f;

    public PlayerCombatStats() {

        for (SpellSchool school : SpellSchool.values()) {

            damageBonus.put(school, 0.0f);
            resist.put(school, 0.0f);
            pierce.put(school, 0.0f);
        }
    }

    // SCHOOL

    public SpellSchool getPrimarySchool() {
        return primarySchool;
    }

    public void setPrimarySchool(SpellSchool primarySchool) {
        this.primarySchool = primarySchool;
    }

    // DAMAGE

    public float getDamageBonus(SpellSchool school) {
        return damageBonus.get(school);
    }

    public void setDamageBonus(
            SpellSchool school,
            float value
    ) {
        damageBonus.put(school, value);
    }

    // RESIST

    public float getResist(SpellSchool school) {
        return resist.get(school);
    }

    public void setResist(
            SpellSchool school,
            float value
    ) {
        resist.put(school, value);
    }

    // PIERCE

    public float getPierce(SpellSchool school) {
        return pierce.get(school);
    }

    public void setPierce(
            SpellSchool school,
            float value
    ) {
        pierce.put(school, value);
    }
    // POWERPIP
    public float getPowerPipChance() {
        return powerPipChance;
    }

    public void setPowerPipChance(float powerPipChance) {
        this.powerPipChance = powerPipChance;
    }

    public void addPowerPipChance(float amount) {
        this.powerPipChance += amount;
    }

}