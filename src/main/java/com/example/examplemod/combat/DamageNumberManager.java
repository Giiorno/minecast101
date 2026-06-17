package com.example.examplemod.combat;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.FloatTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.entity.Display;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.storage.TagValueInput;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class DamageNumberManager {

    private static final List<ActiveDamageNumber> DAMAGE_NUMBERS =
            new ArrayList<>();

    private static final int LIFETIME_TICKS = 25;

    public static void spawn(
            LivingEntity target,
            float damage
    ) {
        if (!(target.level() instanceof ServerLevel level)) {
            return;
        }

        if (damage <= 0) {
            return;
        }

        Display.TextDisplay number =
                new Display.TextDisplay(
                        EntityType.TEXT_DISPLAY,
                        level
                );

        float scale =
                calculateScale(damage);

        CompoundTag displayData =
                createTextDisplayTag(
                        formatDamage(damage),
                        scale
                );

        number.load(
                TagValueInput.create(
                        ProblemReporter.DISCARDING,
                        level.registryAccess(),
                        displayData
                )
        );

        number.setNoGravity(true);
        number.setInvulnerable(true);

        double randomX =
                (level.getRandom().nextDouble() - 0.5) * 0.35;

        double randomZ =
                (level.getRandom().nextDouble() - 0.5) * 0.35;

        number.setPos(
                target.getX() + randomX,
                target.getY() + target.getBbHeight() + 0.35,
                target.getZ() + randomZ
        );

        level.addFreshEntity(number);

        DAMAGE_NUMBERS.add(
                new ActiveDamageNumber(
                        number,
                        LIFETIME_TICKS
                )
        );
    }

    public static void tick(ServerLevel level) {
        Iterator<ActiveDamageNumber> iterator =
                DAMAGE_NUMBERS.iterator();

        while (iterator.hasNext()) {
            ActiveDamageNumber activeNumber =
                    iterator.next();

            Display.TextDisplay number =
                    activeNumber.number();

            if (number.isRemoved()) {
                iterator.remove();
                continue;
            }

            if (number.level() != level) {
                continue;
            }

            int remainingTicks =
                    activeNumber.remainingTicks();

            if (remainingTicks <= 0) {
                number.discard();
                iterator.remove();
                continue;
            }

            number.setPos(
                    number.getX(),
                    number.getY() + 0.035,
                    number.getZ()
            );

            activeNumber.setRemainingTicks(
                    remainingTicks - 1
            );
        }
    }

    private static CompoundTag createTextDisplayTag(
            String text,
            float scale
    ) {
        CompoundTag tag =
                new CompoundTag();

        tag.put(
                "text",
                createTextComponentTag(text)
        );

        tag.putString(
                "billboard",
                "center"
        );

        tag.putByte(
                "text_opacity",
                (byte) 255
        );

        tag.putInt(
                "background",
                0x00000000
        );

        tag.putBoolean(
                "see_through",
                false
        );

        tag.putBoolean(
                "default_background",
                false
        );

        tag.put(
                "transformation",
                createTransformationTag(scale)
        );

        return tag;
    }

    private static Tag createTextComponentTag(String text) {
        Component component =
                Component.literal(text)
                        .withStyle(
                                ChatFormatting.RED,
                                ChatFormatting.BOLD
                        );

        return ComponentSerialization.CODEC
                .encodeStart(
                        NbtOps.INSTANCE,
                        component
                )
                .getOrThrow();
    }

    private static CompoundTag createTransformationTag(float scale) {
        CompoundTag transformation =
                new CompoundTag();

        transformation.put(
                "translation",
                floatList(0.0f, 0.0f, 0.0f)
        );

        transformation.put(
                "scale",
                floatList(scale, scale, scale)
        );

        transformation.put(
                "left_rotation",
                floatList(0.0f, 0.0f, 0.0f, 1.0f)
        );

        transformation.put(
                "right_rotation",
                floatList(0.0f, 0.0f, 0.0f, 1.0f)
        );

        return transformation;
    }

    private static ListTag floatList(float... values) {
        ListTag list =
                new ListTag();

        for (float value : values) {
            list.add(
                    FloatTag.valueOf(value)
            );
        }

        return list;
    }

    private static String formatDamage(float damage) {
        long rounded =
                Math.round(damage);

        return NumberFormat
                .getIntegerInstance(Locale.GERMANY)
                .format(rounded);
    }

    private static float calculateScale(float damage) {
        float safeDamage =
                Math.max(1.0f, damage);

        float baseDamage =
                100.0f;

        float baseScale =
                1.0f;

        float scale =
                baseScale * (float) Math.pow(
                        2.0,
                        Math.log10(safeDamage / baseDamage)
                );

        return Mth.clamp(
                scale,
                0.75f,
                6.0f
        );
    }

    private static class ActiveDamageNumber {

        private final Display.TextDisplay number;
        private int remainingTicks;

        public ActiveDamageNumber(
                Display.TextDisplay number,
                int remainingTicks
        ) {
            this.number = number;
            this.remainingTicks = remainingTicks;
        }

        public Display.TextDisplay number() {
            return number;
        }

        public int remainingTicks() {
            return remainingTicks;
        }

        public void setRemainingTicks(int remainingTicks) {
            this.remainingTicks = remainingTicks;
        }
    }
}