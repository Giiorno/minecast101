package com.example.examplemod.commands;

import com.example.examplemod.spells.fire.FirecatSpell;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import com.example.examplemod.spells.balance.ScarabSpell;

public class SpellCommands {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {

        dispatcher.register(
                Commands.literal("minecast")
                        .then(
                                Commands.literal("testfirecat")
                                        .executes(context -> {

                                            ServerPlayer player =
                                                    context.getSource().getPlayerOrException();

                                            try {
                                                new FirecatSpell().cast(player, 1);

                                                player.sendSystemMessage(
                                                        Component.literal("Firecat cast!")
                                                );
                                            } catch (IllegalStateException exception) {
                                                player.sendSystemMessage(
                                                        Component.literal(exception.getMessage())
                                                );
                                            }

                                            return 1;
                                        })

                        )
                        .then(
                                Commands.literal("testscarab")
                                        .executes(context -> {

                                            ServerPlayer player =
                                                    context.getSource().getPlayerOrException();

                                            try {
                                                new ScarabSpell().cast(player, 1);

                                                player.sendSystemMessage(
                                                        Component.literal("Scarab cast!")
                                                );
                                            } catch (IllegalStateException exception) {
                                                player.sendSystemMessage(
                                                        Component.literal(exception.getMessage())
                                                );
                                            }

                                            return 1;
                                        })
                        )
        );
    }
}