package com.example.examplemod.spells.balance;

import com.example.examplemod.combat.effects.BladeEffect;
import com.example.examplemod.combat.effects.CombatEffectManager;
import com.example.examplemod.spells.Spell;
import com.example.examplemod.spells.SpellSchool;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class EnhancedBalanceBladeSpell extends Spell {

    public EnhancedBalanceBladeSpell() {
        super(
                "Enhanced Balance Blade",
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
                        1.35f,
                        true,
                        "balance_blade_enhanced"
                )
        );

        caster.sendSystemMessage(
                Component.literal("Enhanced Balance Blade added!")
        );
    }
}