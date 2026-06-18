package com.example.examplemod.combat.effects;

import com.example.examplemod.spells.SpellSchool;

public class BladeEffect {

    private final SpellSchool school;
    private final float multiplier;
    private final boolean universal;
    private final String stackingKey;

    public BladeEffect(
            SpellSchool school,
            float multiplier,
            boolean universal,
            String stackingKey
    ) {
        this.school = school;
        this.multiplier = multiplier;
        this.universal = universal;
        this.stackingKey = stackingKey;
    }

    public SpellSchool getSchool() {
        return school;
    }

    public float getMultiplier() {
        return multiplier;
    }

    public boolean isUniversal() {
        return universal;
    }

    public String getStackingKey() {
        return stackingKey;
    }

    public boolean canApplyTo(SpellSchool spellSchool) {
        if (universal) {
            return true;
        }

        return school == spellSchool;
    }
}