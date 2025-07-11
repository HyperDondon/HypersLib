package com.hyperdondon.lib.power.util;

import org.bukkit.entity.LivingEntity;

public class DamageUtil {
    public static void trueDamage(LivingEntity livingEntity, double hearts) {
        if (hearts <= 0) return;
        if (hearts >= livingEntity.getHealth()) infDamage(livingEntity);
        else {
            if (livingEntity.getHealth() - hearts < 0) {
                infDamage(livingEntity);
                return;
            }
            livingEntity.setHealth(livingEntity.getHealth() - hearts);
            livingEntity.damage(0.0000001);
        }
    }

    private static void infDamage(LivingEntity livingEntity) {
        livingEntity.damage(Integer.MAX_VALUE);
    }
}
