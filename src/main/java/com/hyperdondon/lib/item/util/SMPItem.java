package com.hyperdondon.lib.item.util;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.UUID;

@Getter
@Setter
public abstract class SMPItem<T extends Enum<T>> {
    private final T type;
    private ItemStack item;
    private UUID itemUUID;


    /**
     * @param pType This enum should contain all the SMP items.
     * @param pUUID The id of the item.
     */
    public SMPItem(T pType, UUID pUUID) {
        this.type = pType;
        this.itemUUID = pUUID;
    }

    public static boolean isSMPItem(ItemStack item) {
        if (item == null)
            return false;
        if (!item.hasItemMeta())
            return false;
        return getID(item) != null;
    }

    public static UUID getID(ItemStack item) {
        if (!item.getItemMeta().getPersistentDataContainer().has(ItemConstructor.key("id"), PersistentDataType.STRING))
            return null;
        return UUID.fromString(item.getItemMeta().getPersistentDataContainer().get(ItemConstructor.key("id"), PersistentDataType.STRING));
    }

    public static String getType(ItemStack item) {
        if (item == null) return null;
        if (!item.hasItemMeta()) return null;
        if (!item.getItemMeta().getPersistentDataContainer().has(ItemConstructor.key("type"), PersistentDataType.STRING))
            return null;
        return item.getItemMeta().getPersistentDataContainer().get(ItemConstructor.key("type"), PersistentDataType.STRING);
    }

    public abstract ItemStack toItem(UUID uuid);

    public ItemStack toItem() {
        return toItem(UUID.randomUUID());
    }
}
