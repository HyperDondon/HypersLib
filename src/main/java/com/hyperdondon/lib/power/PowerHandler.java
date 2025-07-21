package com.hyperdondon.lib.power;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.Common;

import java.util.ArrayList;

@Getter
@Setter
public abstract class PowerHandler {
    ArrayList<PowerData> powers = new ArrayList<>();
    ArrayList<Passive> passives = new ArrayList<>();

    public PowerHandler() {
        Common.runTimer(1, () -> {
            onTick();
            for (Passive passive : passives) {
                passive.tick();
            }
            for (PowerData powerData : powers) {
                powerData.getPower().tick();
            }
        });
        Common.runTimer(20, () -> {
            everySecond();
            for (Passive passive : passives) {
                passive.second();
            }
            for (PowerData powerData : powers) {
                powerData.getPower().second();
            }
        });
    }

    public abstract ItemStack getItem();

    public abstract Enum<?> getType();

    protected void onTick() {
    }

    protected void everySecond() {
    }

    public void addPower(PowerData power) {
        powers.add(power);
    }

    public void addPassive(Passive passive) {
        passives.add(passive);
    }

    public void removePower(PowerData power) {
        powers.remove(power);
    }

    public void removePassive(Passive passive) {
        passives.remove(passive);
    }
}
