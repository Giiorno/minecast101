package com.example.examplemod.spells.utility;

import com.example.examplemod.combat.effects.CombatTrapManager;
import com.example.examplemod.combat.effects.TrapEffect;
import com.example.examplemod.spells.Spell;
import com.example.examplemod.spells.SpellSchool;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class TrapSpell extends Spell {

    private static final double RANGE = 20.0;

    private final float multiplier;
    private final boolean universal;
    private final String stackingKey;

    public TrapSpell(
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

        LivingEntity target =
                findLookedAtTarget(caster);

        if (target == null) {
            caster.sendSystemMessage(
                    Component.literal("No target found for trap!")
            );
            return;
        }

        CombatTrapManager.get(target).addTrap(
                new TrapEffect(
                        getSchool(),
                        multiplier,
                        universal,
                        stackingKey
                )
        );

        caster.sendSystemMessage(
                Component.literal(getName() + " applied to " + target.getName().getString() + "!")
        );
    }

    private LivingEntity findLookedAtTarget(Player caster) {

        Vec3 eyePosition =
                caster.getEyePosition();

        Vec3 lookDirection =
                caster.getLookAngle().normalize();

        Vec3 reachEndpoint =
                eyePosition.add(lookDirection.scale(RANGE));

        AABB searchBox =
                caster.getBoundingBox()
                        .expandTowards(lookDirection.scale(RANGE))
                        .inflate(1.0);

        LivingEntity closestTarget = null;
        double closestDistanceSq = Double.MAX_VALUE;

        for (LivingEntity candidate : caster.level().getEntitiesOfClass(
                LivingEntity.class,
                searchBox,
                entity -> entity != caster && entity.isAlive() && entity.isPickable()
        )) {

            AABB candidateBox =
                    candidate.getBoundingBox().inflate(0.3);

            var hit =
                    candidateBox.clip(eyePosition, reachEndpoint);

            if (hit.isEmpty()) {
                continue;
            }

            double distanceSq =
                    eyePosition.distanceToSqr(hit.get());

            if (distanceSq < closestDistanceSq) {
                closestDistanceSq = distanceSq;
                closestTarget = candidate;
            }
        }

        return closestTarget;
    }
}