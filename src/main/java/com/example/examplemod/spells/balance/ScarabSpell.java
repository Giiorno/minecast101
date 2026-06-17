package com.example.examplemod.spells.balance;

import com.example.examplemod.spells.Spell;
import com.example.examplemod.spells.SpellCast;
import com.example.examplemod.spells.SpellDamageCalculator;
import com.example.examplemod.spells.SpellSchool;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class ScarabSpell extends Spell {

    public ScarabSpell() {
        super(
                "Scarab",
                SpellSchool.BALANCE,
                1,
                75.0f
        );
    }

    @Override
    public void cast(Player caster, int spentPips) {
        if (caster.level().isClientSide()) {
            return;
        }

        SpellCast cast =
                SpellDamageCalculator.createCast(
                        caster,
                        this,
                        spentPips
                );

        Vec3 lookDirection =
                caster.getLookAngle().normalize();

        Vec3 scarabPosition =
                caster.position()
                        .add(0, 0.2, 0)
                        .add(lookDirection.scale(1.0));

        List<LivingEntity> targets =
                findTargetsInFrontOfScarab(
                        caster,
                        scarabPosition,
                        lookDirection
                );

        for (LivingEntity target : targets) {
            SpellDamageCalculator.applyHit(
                    cast,
                    target
            );
        }
    }

    private List<LivingEntity> findTargetsInFrontOfScarab(
            Player caster,
            Vec3 scarabPosition,
            Vec3 lookDirection
    ) {
        AABB searchBox =
                new AABB(
                        scarabPosition.x - 5,
                        scarabPosition.y - 2,
                        scarabPosition.z - 5,
                        scarabPosition.x + 5,
                        scarabPosition.y + 2,
                        scarabPosition.z + 5
                );

        return caster.level().getEntitiesOfClass(
                LivingEntity.class,
                searchBox,
                target -> isValidScarabTarget(
                        caster,
                        target,
                        scarabPosition,
                        lookDirection
                )
        );
    }

    private boolean isValidScarabTarget(
            Player caster,
            LivingEntity target,
            Vec3 scarabPosition,
            Vec3 lookDirection
    ) {
        if (target == caster) {
            return false;
        }

        if (!target.isAlive()) {
            return false;
        }

        Vec3 targetPosition =
                target.position()
                        .add(0, target.getBbHeight() / 2.0, 0);

        Vec3 toTarget =
                targetPosition.subtract(scarabPosition);

        double forwardDistance =
                toTarget.dot(lookDirection);

        if (forwardDistance < 0) {
            return false;
        }

        if (forwardDistance > 4.0) {
            return false;
        }

        Vec3 closestPointForward =
                lookDirection.scale(forwardDistance);

        double sideDistance =
                toTarget.subtract(closestPointForward).length();

        return sideDistance <= 1.5;
    }
}