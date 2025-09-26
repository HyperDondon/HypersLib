package com.hyperdondon.lib.item.antipocket;

import com.hyperdondon.lib.item.util.SMPItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.annotation.AutoRegister;

import java.util.*;

@AutoRegister
public final class AntiDrop implements Listener {
    private final Map<UUID, ItemStack> lastCursorItem = new HashMap<UUID, ItemStack>();

    //Make the slots checked only the ones you can pick up
    public static boolean canHoldTwoWaterBuckets(Player p) {
        int emptySlots = 0;
        for (ItemStack item : p.getInventory().getStorageContents())
            if (item.getType() == Material.AIR) {
                emptySlots++;
                if (emptySlots >= 2) return true;
            }
        return false;
    }

    @EventHandler
    public void cacheCursorSlot(InventoryClickEvent event) {
        if (!(event.getClick() == ClickType.LEFT || event.getClick() == ClickType.RIGHT || event.getClick() == ClickType.CREATIVE))
            return;
        //Bukkit.broadcastMessage(e.getClick().toString());
        ItemStack item = event.getCurrentItem();
        //Bukkit.broadcastMessage(item.toString());
        if (lastCursorItem.containsKey(event.getWhoClicked().getUniqueId())) {
            lastCursorItem.replace(event.getWhoClicked().getUniqueId(), item);
            return;
        }
        lastCursorItem.put(event.getWhoClicked().getUniqueId(), item);
    }

    @EventHandler
    public void clearOnLeave(PlayerQuitEvent event) {
        lastCursorItem.remove(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        if (SMPItem.isSMPItem(event.getItemDrop().getItemStack())) event.setCancelled(true);
    }

    //Don't use the Paper event, it just doesn't work.
    @EventHandler
    public void onPickUp(PlayerPickupItemEvent event) {
        if (!lastCursorItem.containsKey(event.getPlayer().getUniqueId())) return;
        if (!SMPItem.isSMPItem(lastCursorItem.get(event.getPlayer().getUniqueId()))) return;
        if (canHoldTwoWaterBuckets(event.getPlayer())) return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        //We gotta clone it somehow
        List<ItemStack> smpItems = new ArrayList<>();
        for (ItemStack item : event.getDrops()) {
            if (item == null) continue;
            if (SMPItem.isSMPItem(item)) smpItems.add(item);
        }
        for (ItemStack item : smpItems) {
            event.getDrops().remove(item);
            if (event.getKeepInventory()) continue;
            Common.runLater(1, () -> event.getPlayer().getInventory().addItem(item));
        }
    }
}
