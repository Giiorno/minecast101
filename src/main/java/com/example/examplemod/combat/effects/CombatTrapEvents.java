package com.example.examplemod.combat.effects;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

public class CombatTrapEvents {

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {

        LivingEntity entity =
                event.getEntity();

        CombatTrapManager.clear(entity);
    }

    @SubscribeEvent
    public static void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {

        Player player =
                event.getEntity();

        CombatTrapManager.clear(player);
    }
}