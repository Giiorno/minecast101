package com.example.examplemod.spells.fire;

import com.example.examplemod.spells.Spell;
import com.example.examplemod.spells.SpellCast;
import com.example.examplemod.spells.SpellDamageCalculator;
import com.example.examplemod.spells.SpellSchool;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class FirecatSpell extends Spell {

    public FirecatSpell() {
        super(
                "Firecat",
                SpellSchool.FIRE,
                1,
                90.0f
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

        Vec3 firecatPosition =
                caster.position()
                        .add(0, 0.2, 0)
                        .add(lookDirection.scale(1.0));

        List<LivingEntity> targets =
                findTargetsInFrontOfFirecat(
                        caster,
                        firecatPosition,
                        lookDirection
                );

        for (LivingEntity target : targets) {
            SpellDamageCalculator.applyHit(
                    cast,
                    target
            );
        }

        // Hier später:
        // Partikel
        // Sound
        // Firecat-Animation
    }

    private List<LivingEntity> findTargetsInFrontOfFirecat(
            Player caster,
            Vec3 firecatPosition,
            Vec3 lookDirection
    ) {
        AABB searchBox =
                new AABB(
                        firecatPosition.x - 6,
                        firecatPosition.y - 2,
                        firecatPosition.z - 6,
                        firecatPosition.x + 6,
                        firecatPosition.y + 2,
                        firecatPosition.z + 6
                );

        return caster.level().getEntitiesOfClass(
                LivingEntity.class,
                searchBox,
                target -> isValidFirecatTarget(
                        caster,
                        target,
                        firecatPosition,
                        lookDirection
                )
        );
    }

    private boolean isValidFirecatTarget(
            Player caster,
            LivingEntity target,
            Vec3 firecatPosition,
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
                targetPosition.subtract(firecatPosition);

        double forwardDistance =
                toTarget.dot(lookDirection);

        if (forwardDistance < 0) {
            return false;
        }

        if (forwardDistance > 5.0) {
            return false;
        }

        Vec3 closestPointForward =
                lookDirection.scale(forwardDistance);

        double sideDistance =
                toTarget.subtract(closestPointForward).length();

        return sideDistance <= 1.5;
    }
}