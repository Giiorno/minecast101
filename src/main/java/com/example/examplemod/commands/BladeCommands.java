package com.example.examplemod.commands;

import com.example.examplemod.combat.effects.CombatEffectManager;
import com.example.examplemod.spells.balance.BalanceBladeSpell;
import com.example.examplemod.spells.balance.EnhancedBalanceBladeSpell;
import com.example.examplemod.spells.fire.EnhancedFireBladeSpell;
import com.example.examplemod.spells.fire.FireBladeSpell;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class BladeCommands {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {

        dispatcher.register(
                Commands.literal("minecast")
                        .then(
                                Commands.literal("blade")

                                        .then(
                                                Commands.literal("fire")
                                                        .executes(context -> {

                                                            ServerPlayer player =
                                                                    context.getSource().getPlayerOrException();

                                                            new FireBladeSpell().cast(player, 0);

                                                            return 1;
                                                        })
                                        )

                                        .then(
                                                Commands.literal("fire_enhanced")
                                                        .executes(context -> {

                                                            ServerPlayer player =
                                                                    context.getSource().getPlayerOrException();

                                                            new EnhancedFireBladeSpell().cast(player, 0);

                                                            return 1;
                                                        })
                                        )

                                        .then(
                                                Commands.literal("balance")
                                                        .executes(context -> {

                                                            ServerPlayer player =
                                                                    context.getSource().getPlayerOrException();

                                                            new BalanceBladeSpell().cast(player, 0);

                                                            return 1;
                                                        })
                                        )

                                        .then(
                                                Commands.literal("balance_enhanced")
                                                        .executes(context -> {

                                                            ServerPlayer player =
                                                                    context.getSource().getPlayerOrException();

                                                            new EnhancedBalanceBladeSpell().cast(player, 0);

                                                            return 1;
                                                        })
                                        )

                                        .then(
                                                Commands.literal("clear")
                                                        .executes(context -> {

                                                            ServerPlayer player =
                                                                    context.getSource().getPlayerOrException();

                                                            CombatEffectManager.clear(player);

                                                            player.sendSystemMessage(
                                                                    Component.literal("All blades cleared!")
                                                            );

                                                            return 1;
                                                        })
                                        )

                                        .then(
                                                Commands.literal("count")
                                                        .executes(context -> {

                                                            ServerPlayer player =
                                                                    context.getSource().getPlayerOrException();

                                                            int bladeCount =
                                                                    CombatEffectManager
                                                                            .get(player)
                                                                            .getBladeCount();

                                                            player.sendSystemMessage(
                                                                    Component.literal("Active blades: " + bladeCount)
                                                            );

                                                            return 1;
                                                        })
                                        )

                                        .then(
                                                Commands.literal("test_all")
                                                        .executes(context -> {

                                                            ServerPlayer player =
                                                                    context.getSource().getPlayerOrException();

                                                            new FireBladeSpell().cast(player, 0);
                                                            new EnhancedFireBladeSpell().cast(player, 0);
                                                            new BalanceBladeSpell().cast(player, 0);
                                                            new EnhancedBalanceBladeSpell().cast(player, 0);

                                                            player.sendSystemMessage(
                                                                    Component.literal("Added all test blades!")
                                                            );

                                                            return 1;
                                                        })
                                        )
                        )
        );
    }
}