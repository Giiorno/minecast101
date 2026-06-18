package com.example.examplemod.spells.utility;

import com.example.examplemod.combat.effects.CombatEffectManager;
import com.example.examplemod.spells.Spell;
import com.example.examplemod.spells.SpellSchool;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class ClearBladesSpell extends Spell {

    public ClearBladesSpell() {
        super(
                "Clear Blades",
                SpellSchool.BALANCE,
                0,
                0.0f
        );
    }

    @Override
    public void cast(Player caster, int spentPips) {

        if (caster.level().isClientSide()) {
            return;
        }

        CombatEffectManager.clear(caster);

        caster.sendSystemMessage(
                Component.literal("Your blades have been cleared!")
        );
    }
}