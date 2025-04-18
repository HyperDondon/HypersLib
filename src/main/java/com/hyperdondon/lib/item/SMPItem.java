package com.hyperdondon.lib.item;

import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;
import java.util.UUID;
public class SMPItem {
    public static boolean isSMPItem(ItemStack item) {return getID(item) != null;}

    public static UUID getID(ItemStack item) {
        if (!item.getItemMeta().getPersistentDataContainer().has(ItemConstructor.key("id"), PersistentDataType.STRING)) return null;
        return UUID.fromString(Objects.requireNonNull(
                item.getItemMeta().getPersistentDataContainer().get(ItemConstructor.key("id"), PersistentDataType.STRING)
        ));
    }

    public static UUID getType(ItemStack item) {
        if (!item.getItemMeta().getPersistentDataContainer().has(ItemConstructor.key("type"), PersistentDataType.STRING)) return null;
        return UUID.fromString(Objects.requireNonNull(
                item.getItemMeta().getPersistentDataContainer().get(ItemConstructor.key("type"), PersistentDataType.STRING)
        ));
    }
}
