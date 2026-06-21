package com.example.examplemod.spells.utility;

import com.example.examplemod.combat.effects.BladeEffect;
import com.example.examplemod.combat.effects.CombatEffectManager;
import com.example.examplemod.spells.Spell;
import com.example.examplemod.spells.SpellSchool;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class BladeSpell extends Spell {

    private final float multiplier;
    private final boolean universal;
    private final String stackingKey;

    public BladeSpell(
            String name,
            SpellSchool school,
            float multiplier,
            boolean universal,
            String stackingKey
    ) {
        super(name, school, 0, 0.0f);
        this.multiplier = multiplier;
        this.universal = universal;
        this.stackingKey = stackingKey;
    }

    @Override
    public void cast(Player caster, int spentPips) {

        if (caster.level().isClientSide()) {
            return;
        }

        CombatEffectManager.get(caster).addBlade(
                new BladeEffect(
                        getSchool(),
                        multiplier,
                        universal,
                        stackingKey
                )
        );

        caster.sendSystemMessage(
                Component.literal(getName() + " added!")
        );
    }
}