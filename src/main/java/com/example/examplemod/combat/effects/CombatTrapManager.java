package com.example.examplemod.combat.effects;

import net.minecraft.world.entity.LivingEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CombatTrapManager {

    private static final Map<UUID, PlayerCombatTraps> ENTITY_TRAPS =
            new HashMap<>();

    public static PlayerCombatTraps get(LivingEntity entity) {
        return ENTITY_TRAPS.computeIfAbsent(
                entity.getUUID(),
                id -> new PlayerCombatTraps()
        );
    }

    public static void clear(LivingEntity entity) {
        get(entity).clearTraps();
    }
}