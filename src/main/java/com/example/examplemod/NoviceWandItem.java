package com.example.examplemod;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.network.chat.Component;

public class NoviceWandItem extends Item {

    public NoviceWandItem(Properties properties) {
        super(properties);
    }

    @Override

    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide()) {
            player.sendSystemMessage(Component.literal("Fire Cat cast!"));
        }

        return InteractionResult.SUCCESS;
    }
}