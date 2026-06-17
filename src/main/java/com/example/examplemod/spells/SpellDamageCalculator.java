package com.example.examplemod.spells;

import com.example.examplemod.combat.CombatManager;
import com.example.examplemod.combat.PlayerCombatStats;
import com.example.examplemod.pips.PipManager;
import com.example.examplemod.pips.PlayerPips;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class SpellDamageCalculator {

    public static SpellCast createCast(
            Player caster,
            Spell spell,
            int spentPips
    ) {
        PlayerCombatStats casterStats =
                CombatManager.getStats(caster);

        PlayerPips casterPips =
                PipManager.get(caster);

        if (!casterPips.canSpend(
                spell.getPipCost(),
                spell.getSchool(),
                casterStats.getPrimarySchool()
        )) {
            throw new IllegalStateException(
                    "Not enough pips to cast " + spell.getName()
            );
        }

        casterPips.spend(
                spell.getPipCost(),
                spell.getSchool(),
                casterStats.getPrimarySchool()
        );

        float damage =
                spell.getBaseDamage(spentPips);

        float damageBonus =
                casterStats.getDamageBonus(
                        spell.getSchool()
                );

        damage *= 1 + (damageBonus / 100f);

        float pierce =
                casterStats.getPierce(
                        spell.getSchool()
                );

        return new SpellCast(
                caster,
                spell,
                damage,
                pierce,
                spentPips
        );
    }

    public static void applyHit(
            SpellCast cast,
            LivingEntity target
    ) {
        float damage =
                cast.getOutgoingDamage();

        if (target instanceof Player targetPlayer) {
            PlayerCombatStats targetStats =
                    CombatManager.getStats(targetPlayer);

            float resist =
                    targetStats.getResist(
                            cast.getSchool()
                    );

            resist -= cast.getPierce();

            if (resist < 0) {
                resist = 0;
            }

            damage *= 1 - (resist / 100f);
        }

        if (damage < 0) {
            damage = 0;
        }

        target.hurt(
                cast.getCaster()
                        .damageSources()
                        .magic(),
                damage
        );
    }
}