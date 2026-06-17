package com.example.examplemod.items;

import com.example.examplemod.spells.Spell;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import java.util.function.Supplier;

public class SpellCardItem extends Item {

    private final Supplier<Spell> spellSupplier;

    public SpellCardItem(
            Properties properties,
            Supplier<Spell> spellSupplier
    ) {
        super(properties);
        this.spellSupplier = spellSupplier;
    }

    @Override
    public InteractionResult use(
            Level level,
            Player player,
            InteractionHand hand
    ) {
        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        try {
            Spell spell = spellSupplier.get();

            spell.cast(player, 1);

            player.sendSystemMessage(
                    Component.literal("Casted " + spell.getName())
            );

            return InteractionResult.SUCCESS;

        } catch (IllegalStateException exception) {
            player.sendSystemMessage(
                    Component.literal(exception.getMessage())
            );

            return InteractionResult.FAIL;
        }
    }
}