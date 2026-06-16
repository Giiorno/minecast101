package com.example.examplemod.pips;

import net.minecraft.network.chat.Component;
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

        PlayerPips pips = PipManager.get(player);

        if (pips.getTotalPips() >= 7) {
            return;
        }

        if (RANDOM.nextFloat() < 0.20f) {
            pips.addPowerPip();
            player.sendSystemMessage(Component.literal("Power Pip gained!"));
        } else {
            pips.addNormalPip();
            player.sendSystemMessage(Component.literal("Pip gained!"));
        }

        player.sendSystemMessage(
                Component.literal(
                        "Pips: " + pips.getPips()
                                + " | Power Pips: "
                                + pips.getPowerPips()
                )
        );
    }
}