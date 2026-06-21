package com.example.examplemod.combat.effects;

import com.example.examplemod.spells.SpellSchool;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PlayerCombatTraps {

    private final List<TrapEffect> traps =
            new ArrayList<>();

    public void addTrap(TrapEffect trap) {

        if (hasTrapWithStackingKey(trap.getStackingKey())) {
            return;
        }

        traps.add(trap);
    }

    public boolean hasTrapWithStackingKey(String stackingKey) {

        for (TrapEffect trap : traps) {
            if (trap.getStackingKey().equals(stackingKey)) {
                return true;
            }
        }

        return false;
    }

    public float consumeMatchingTraps(SpellSchool spellSchool) {

        float multiplier =
                1.0f;

        Iterator<TrapEffect> iterator =
                traps.iterator();

        while (iterator.hasNext()) {
            TrapEffect trap =
                    iterator.next();

            if (!trap.canApplyTo(spellSchool)) {
                continue;
            }

            multiplier *= trap.getMultiplier();

            iterator.remove();
        }

        return multiplier;
    }

    public int getTrapCount() {
        return traps.size();
    }

    public void clearTraps() {
        traps.clear();
    }
}