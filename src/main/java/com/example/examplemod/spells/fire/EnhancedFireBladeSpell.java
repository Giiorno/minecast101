package com.example.examplemod.spells.fire;

import com.example.examplemod.combat.effects.BladeEffect;
import com.example.examplemod.combat.effects.CombatEffectManager;
import com.example.examplemod.spells.Spell;
import com.example.examplemod.spells.SpellSchool;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class EnhancedFireBladeSpell extends Spell {

    public EnhancedFireBladeSpell() {
        super(
                "Enhanced Fire Blade",
                SpellSchool.FIRE,
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
                        SpellSchool.FIRE,
                        1.45f,
                        false,
                        "fire_blade_enhanced"
                )
        );

        caster.sendSystemMessage(
                Component.literal("Enhanced Fire Blade added!")
        );
    }
}