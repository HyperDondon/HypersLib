package com.hyperdondon.lib.power;

import com.hyperdondon.lib.item.util.SMPItem;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public abstract class Power {
    public abstract void cast(Player p, @Nullable SMPItem<?> item, @Nullable Event event);

    public abstract void uncast(Player p, @Nullable SMPItem<?> item, @Nullable Event event);

    public void tick() {
    }

    public void second() {
    }
}

