package com.example.examplemod.items;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.network.chat.Component;
import com.example.examplemod.pips.PlayerPips;
import com.example.examplemod.pips.PipManager;
import com.example.examplemod.pips.PlayerPips;

public class NoviceWandItem extends Item {

    public NoviceWandItem(Properties properties) {
        super(properties);
    }

    @Override

    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide()) {
            PlayerPips pips = PipManager.get(player);

            player.sendSystemMessage(
                    Component.literal(
                            "Pips: " + pips.getPips()
                                    + " | Power Pips: "
                                    + pips.getPowerPips()
                    )
            );
        }

        return InteractionResult.SUCCESS;
    }
}