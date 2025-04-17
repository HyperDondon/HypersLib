package com.hyperdondon.lib.powers;

import org.bukkit.entity.Player;

public abstract class Power {
    public abstract void cast(Player p);
    public abstract void uncast(Player p);
}

