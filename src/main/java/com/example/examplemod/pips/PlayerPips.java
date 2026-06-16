package com.example.examplemod.pips;

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

    public void clear() {
        pips = 0;
        powerPips = 0;
    }
}