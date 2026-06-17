package com.example.examplemod.pips;

import com.example.examplemod.spells.SpellSchool;

public class PlayerPips {

    private int pips = 0;
    private int powerPips = 0;

    public int getPips() {
        return pips;
    }

    public int getPowerPips() {
        return powerPips;
    }

    public void addNormalPip() {
        if (getTotalPips() < 7) {
            pips++;
        }
    }

    public void addPowerPip() {
        if (getTotalPips() < 7) {
            powerPips++;
        }
    }

    public int getTotalPips() {
        return pips + powerPips;
    }

    public int getAvailablePips(
            SpellSchool spellSchool,
            SpellSchool primarySchool
    ) {
        if (spellSchool == primarySchool) {
            return pips + (powerPips * 2);
        }

        return pips + powerPips;
    }

    public boolean canSpend(
            int cost,
            SpellSchool spellSchool,
            SpellSchool primarySchool
    ) {
        return getAvailablePips(spellSchool, primarySchool) >= cost;
    }

    public void spend(
            int cost,
            SpellSchool spellSchool,
            SpellSchool primarySchool
    ) {
        if (!canSpend(cost, spellSchool, primarySchool)) {
            throw new IllegalStateException("Not enough pips.");
        }

        if (spellSchool == primarySchool) {
            spendWithPowerPips(cost);
        } else {
            spendAsNormalPips(cost);
        }
    }

    private void spendWithPowerPips(int cost) {
        int remaining = cost;

        while (remaining >= 2 && powerPips > 0) {
            powerPips--;
            remaining -= 2;
        }

        while (remaining > 0 && pips > 0) {
            pips--;
            remaining--;
        }

        if (remaining > 0 && powerPips > 0) {
            powerPips--;
            remaining = 0;
        }
    }

    private void spendAsNormalPips(int cost) {
        int remaining = cost;

        while (remaining > 0 && pips > 0) {
            pips--;
            remaining--;
        }

        while (remaining > 0 && powerPips > 0) {
            powerPips--;
            remaining--;
        }
    }

    public void clear() {
        pips = 0;
        powerPips = 0;
    }
}