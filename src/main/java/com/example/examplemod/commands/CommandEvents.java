package com.example.examplemod.commands;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import com.example.examplemod.pips.PipCommands;

public class CommandEvents {

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        PipCommands.register(event.getDispatcher());
    }
}