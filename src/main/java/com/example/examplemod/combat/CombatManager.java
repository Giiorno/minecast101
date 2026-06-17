package com.example.examplemod.combat;

import net.minecraft.world.entity.player.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CombatManager {

    private static final Map<UUID, PlayerCombatStats> PLAYER_STATS =
            new HashMap<>();

    public static PlayerCombatStats getStats(Player player) {

        return PLAYER_STATS.computeIfAbsent(
                player.getUUID(),
                id -> new PlayerCombatStats()
        );
    }
}