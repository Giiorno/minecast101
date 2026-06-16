package com.example.examplemod.gui;

import com.example.examplemod.Minecast101;
import com.example.examplemod.pips.PipManager;
import com.example.examplemod.pips.PlayerPips;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.resources.Identifier;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.minecraft.network.chat.Component;

public class PipHudOverlay {

    public static final Identifier PIP_HUD =
            Identifier.fromNamespaceAndPath(Minecast101.MODID, "pip_hud");

    @SubscribeEvent
    public static void registerGuiLayers(RegisterGuiLayersEvent event) {
        event.registerAboveAll(PIP_HUD, (guiGraphics, deltaTracker) -> render(guiGraphics));
    }

    private static void render(GuiGraphicsExtractor guiGraphics) {
        Minecraft minecraft = Minecraft.getInstance();

        if (minecraft.player == null) {
            return;
        }

        PlayerPips pips = PipManager.get(minecraft.player);

        StringBuilder pipDisplay = new StringBuilder();

        for (int i = 0; i < pips.getPips(); i++) {
            pipDisplay.append("○ ");
        }

        for (int i = 0; i < pips.getPowerPips(); i++) {
            pipDisplay.append("✦ ");
        }

        String text = pipDisplay.toString();

        int textWidth = minecraft.font.width(text);

        guiGraphics.textWithBackdrop(
                minecraft.font,
                Component.literal(text),
                10,
                10,
                textWidth,
                0xFFFFFFFF   // opaque white, ARGB
        );
    }
}