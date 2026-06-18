package com.example.examplemod.combat.effects;

import com.example.examplemod.spells.SpellSchool;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PlayerCombatEffects {

    private final List<BladeEffect> blades =
            new ArrayList<>();

    public void addBlade(BladeEffect blade) {

        if (hasBladeWithStackingKey(blade.getStackingKey())) {
            return;
        }

        blades.add(blade);
    }

    public boolean hasBladeWithStackingKey(String stackingKey) {

        for (BladeEffect blade : blades) {
            if (blade.getStackingKey().equals(stackingKey)) {
                return true;
            }
        }

        return false;
    }

    public float consumeMatchingBlades(SpellSchool spellSchool) {

        float multiplier =
                1.0f;

        Iterator<BladeEffect> iterator =
                blades.iterator();

        while (iterator.hasNext()) {
            BladeEffect blade =
                    iterator.next();

            if (!blade.canApplyTo(spellSchool)) {
                continue;
            }

            multiplier *= blade.getMultiplier();

            iterator.remove();
        }

        return multiplier;
    }

    public int getBladeCount() {
        return blades.size();
    }

    public void clearBlades() {
        blades.clear();
    }
}