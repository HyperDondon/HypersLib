package com.hyperdondon.lib.item;


import com.hyperdondon.lib.SMPPlugin;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;
import java.util.UUID;

public class ItemConstructor {
    public static ItemStack construct(Component display, int cmd, Material material, String type, UUID id, Component... lore) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.displayName(display);
        itemMeta.lore(Arrays.stream(lore).toList());
        itemMeta.setCustomModelData(cmd);
        itemMeta.getPersistentDataContainer().set(key("type"), PersistentDataType.STRING, type);
        itemMeta.getPersistentDataContainer().set(key("id"), PersistentDataType.STRING, id.toString());
        for (ItemFlag flag : ItemFlag.values()) itemMeta.addItemFlags(flag);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static NamespacedKey key(String id) {
        return new NamespacedKey(SMPPlugin.getInstance(), id);
    }
}
