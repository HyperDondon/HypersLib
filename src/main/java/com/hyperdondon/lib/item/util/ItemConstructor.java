package com.hyperdondon.lib.item.util;


import com.hyperdondon.lib.SMPPlugin;
import com.hyperdondon.lib.util.format.ComponentWrapper;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public interface ItemConstructor {
    static NamespacedKey key(String id) {
        return new NamespacedKey(SMPPlugin.getInstance(), id);
    }

    default ItemStack construct(ComponentWrapper display, @Nullable Integer cmd, Material material, Enum<?> type, UUID id, ComponentWrapper lore) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(display.toSectionSign());
        itemMeta.setLore(List.of(lore.toSectionSign().split("\n")));
        if (!Objects.isNull(cmd))
            itemMeta.setCustomModelData(cmd);
        itemMeta.getPersistentDataContainer().set(key("type"), PersistentDataType.STRING, type.toString());
        itemMeta.getPersistentDataContainer().set(key("id"), PersistentDataType.STRING, id.toString());
        for (ItemFlag flag : ItemFlag.values()) itemMeta.addItemFlags(flag);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
