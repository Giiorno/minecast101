package com.example.examplemod.combat;

import net.minecraft.server.level.ServerLevel;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

public class DamageNumberEvents {

    @SubscribeEvent
    public static void onLevelTick(LevelTickEvent.Post event) {

        if (!(event.getLevel() instanceof ServerLevel level)) {
            return;
        }

        DamageNumberManager.tick(level);
    }
}