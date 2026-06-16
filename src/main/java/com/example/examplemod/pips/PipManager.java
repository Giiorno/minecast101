package com.example.examplemod.pips;

import net.minecraft.world.entity.player.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PipManager {
    private static final Map<UUID, PlayerPips> PLAYER_PIPS = new HashMap<>();

    public static PlayerPips get(Player player) {
        return PLAYER_PIPS.computeIfAbsent(player.getUUID(), id -> new PlayerPips());
    }

    public static void reset(Player player) {
        get(player).clear();
    }
}