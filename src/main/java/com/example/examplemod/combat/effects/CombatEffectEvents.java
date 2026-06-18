package com.example.examplemod.combat.effects;

import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

public class CombatEffectEvents {

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {

        if (!(event.getEntity() instanceof Player player)) {
            return;
        }

        CombatEffectManager.clear(player);
    }

    @SubscribeEvent
    public static void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {

        Player player =
                event.getEntity();

        CombatEffectManager.clear(player);
    }
}