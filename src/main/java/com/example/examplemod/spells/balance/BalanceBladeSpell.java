package com.example.examplemod.spells.balance;

import com.example.examplemod.combat.effects.BladeEffect;
import com.example.examplemod.combat.effects.CombatEffectManager;
import com.example.examplemod.spells.Spell;
import com.example.examplemod.spells.SpellSchool;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class BalanceBladeSpell extends Spell {

    public BalanceBladeSpell() {
        super(
                "Balance Blade",
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

        CombatEffectManager.get(caster).addBlade(
                new BladeEffect(
                        SpellSchool.BALANCE,
                        1.25f,
                        true,
                        "balance_blade"
                )
        );

        caster.sendSystemMessage(
                Component.literal("Balance Blade added!")
        );
    }
}