package com.example.examplemod.pips;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class PipCommands {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {

        dispatcher.register(
                Commands.literal("minecast")
                        .then(
                                Commands.literal("resetpips")
                                        .executes(context -> {

                                            ServerPlayer player =
                                                    context.getSource().getPlayerOrException();

                                            PipManager.reset(player);

                                            player.sendSystemMessage(
                                                    Component.literal("Pips reset!")
                                            );

                                            return 1;
                                        })
                        )
        );
    }
}