package com.hyperdondon.lib.power;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.potion.PotionEffectType;
@Getter
@Setter
public abstract class PotionEffectPassive extends Passive {
    PotionEffectType potionEffectType;
    int amplifier;

    public PotionEffectPassive(PotionEffectType pPotionEffectType, int pAmplifier) {
        setAmplifier(pAmplifier);
        setPotionEffectType(pPotionEffectType);
    }
}
