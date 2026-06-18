package com.example.examplemod.commands;

import com.example.examplemod.pips.PipCommands;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

public class CommandEvents {

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        PipCommands.register(event.getDispatcher());
        SpellCommands.register(event.getDispatcher());
        BladeCommands.register(event.getDispatcher());
    }
}