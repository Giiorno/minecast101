package com.example.examplemod.commands;

import com.example.examplemod.spells.SpellSchool;
import com.example.examplemod.spells.utility.TrapSpell;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;

public class TrapCommands {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {

        dispatcher.register(
                Commands.literal("minecast")
                        .then(
                                Commands.literal("trap")

                                        .then(
                                                Commands.literal("fire")
                                                        .executes(context -> {

                                                            ServerPlayer player =
                                                                    context.getSource().getPlayerOrException();

                                                            new TrapSpell(
                                                                    "Fire Trap",
                                                                    SpellSchool.FIRE,
                                                                    1.30f,
                                                                    false,
                                                                    "fire_trap"
                                                            ).cast(player, 0);

                                                            return 1;
                                                        })
                                        )

                                        .then(
                                                Commands.literal("balance")
                                                        .executes(context -> {

                                                            ServerPlayer player =
                                                                    context.getSource().getPlayerOrException();

                                                            new TrapSpell(
                                                                    "Balance Trap",
                                                                    SpellSchool.BALANCE,
                                                                    1.25f,
                                                                    true,
                                                                    "balance_trap"
                                                            ).cast(player, 0);

                                                            return 1;
                                                        })
                                        )
                        )
        );
    }
}