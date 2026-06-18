package com.example.examplemod.combat.effects;

import net.minecraft.world.entity.player.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CombatEffectManager {

    private static final Map<UUID, PlayerCombatEffects> PLAYER_EFFECTS =
            new HashMap<>();

    public static PlayerCombatEffects get(Player player) {
        return PLAYER_EFFECTS.computeIfAbsent(
                player.getUUID(),
                id -> new PlayerCombatEffects()
        );
    }

    public static void clear(Player player) {
        get(player).clearBlades();
    }
}