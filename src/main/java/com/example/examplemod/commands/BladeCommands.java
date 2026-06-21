package com.example.examplemod.commands;

import com.example.examplemod.combat.effects.CombatEffectManager;
import com.example.examplemod.spells.SpellSchool;
import com.example.examplemod.spells.utility.BladeSpell;
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

                                                            new BladeSpell("Fire Blade", SpellSchool.FIRE, 1.35f, false, "fire_blade").cast(player, 0);

                                                            return 1;
                                                        })
                                        )

                                        .then(
                                                Commands.literal("fire_enhanced")
                                                        .executes(context -> {

                                                            ServerPlayer player =
                                                                    context.getSource().getPlayerOrException();

                                                            new BladeSpell("Enhanced Fire Blade", SpellSchool.FIRE, 1.45f, false, "fire_blade_enhanced").cast(player, 0);

                                                            return 1;
                                                        })
                                        )

                                        .then(
                                                Commands.literal("balance")
                                                        .executes(context -> {

                                                            ServerPlayer player =
                                                                    context.getSource().getPlayerOrException();

                                                            new BladeSpell("Balance Blade", SpellSchool.BALANCE, 1.25f, true, "balance_blade").cast(player, 0);

                                                            return 1;
                                                        })
                                        )

                                        .then(
                                                Commands.literal("balance_enhanced")
                                                        .executes(context -> {

                                                            ServerPlayer player =
                                                                    context.getSource().getPlayerOrException();

                                                            new BladeSpell("Enhanced Balance Blade", SpellSchool.BALANCE, 1.35f, true, "balance_blade_enhanced").cast(player, 0);

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

                                                            new BladeSpell("Fire Blade", SpellSchool.FIRE, 1.35f, false, "fire_blade").cast(player, 0);
                                                            new BladeSpell("Enhanced Fire Blade", SpellSchool.FIRE, 1.45f, false, "fire_blade_enhanced").cast(player, 0);
                                                            new BladeSpell("Balance Blade", SpellSchool.BALANCE, 1.25f, true, "balance_blade").cast(player, 0);
                                                            new BladeSpell("Enhanced Balance Blade", SpellSchool.BALANCE, 1.35f, true, "balance_blade_enhanced").cast(player, 0);

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