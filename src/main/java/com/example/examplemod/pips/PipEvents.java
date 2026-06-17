package com.example.examplemod.pips;

import com.example.examplemod.combat.CombatManager;
import com.example.examplemod.combat.PlayerCombatStats;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.Random;

public class PipEvents {

    private static final Random RANDOM = new Random();

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();

        if (player.level().isClientSide()) {
            return;
        }

        // Alle 5 Sekunden = 100 Ticks
        if (player.tickCount % 100 != 0) {
            return;
        }

        PlayerPips pips =
                PipManager.get(player);

        if (pips.getTotalPips() >= 7) {
            return;
        }

        PlayerCombatStats stats =
                CombatManager.getStats(player);

        float powerPipChance =
                stats.getPowerPipChance();

        if (powerPipChance < 0) {
            powerPipChance = 0;
        }

        if (powerPipChance > 100) {
            powerPipChance = 100;
        }

        float roll =
                RANDOM.nextFloat() * 100f;

        if (roll < powerPipChance) {
            pips.addPowerPip();
        } else {
            pips.addNormalPip();
        }
    }
}