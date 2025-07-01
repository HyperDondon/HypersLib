package com.hyperdondon.lib.item;


import com.hyperdondon.lib.SMPPlugin;
import com.hyperdondon.lib.util.format.ComponentWrapper;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class ItemConstructor {
    public static ItemStack construct(ComponentWrapper display, int cmd, Material material, String type, UUID id, ComponentWrapper... lore) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.displayName(display.getComponent());
        ArrayList<Component> componentListLore = new ArrayList<>();
        for (ComponentWrapper line : lore) componentListLore.add(line.getComponent());
        itemMeta.lore(componentListLore.stream().toList());
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
