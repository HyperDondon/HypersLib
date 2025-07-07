package com.hyperdondon.lib.item.antipocket;

import com.hyperdondon.lib.item.util.SMPItem;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class AntiDrop implements Listener {
    @Getter
    private static final AntiDrop instance = new AntiDrop();

    private final Map<UUID, ItemStack> lastCursorItem = new HashMap<UUID, ItemStack>();

    //Make the slots checked only the ones you can pick up
    public static boolean canHoldTwoWaterBuckets(Player p) {
        int emptySlots = 0;
        for (ItemStack item : p.getInventory().getStorageContents())
            if (item == null || item.getType() == Material.AIR) {
                emptySlots++;
                if (emptySlots >= 2) return true;
            }
        return false;
    }

    @EventHandler
    public void cacheCursorSlot(InventoryClickEvent e) {
        if (!(e.getClick() == ClickType.LEFT || e.getClick() == ClickType.RIGHT || e.getClick() == ClickType.CREATIVE))
            return;
        //Bukkit.broadcastMessage(e.getClick().toString());
        ItemStack item = e.getCurrentItem();
        //Bukkit.broadcastMessage(item.toString());
        if (lastCursorItem.containsKey(e.getWhoClicked().getUniqueId())) {
            lastCursorItem.replace(e.getWhoClicked().getUniqueId(), item);
            return;
        }
        lastCursorItem.put(e.getWhoClicked().getUniqueId(), item);
    }

    @EventHandler
    public void clearOnLeave(PlayerQuitEvent e) {
        if (lastCursorItem.containsKey(e.getPlayer().getUniqueId())) lastCursorItem.remove(e.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        if (SMPItem.isSMPItem(e.getItemDrop().getItemStack())) e.setCancelled(true);
    }

    //Don't use the Paper event, it just doesn't work.
    @EventHandler
    public void onPickUp(PlayerPickupItemEvent e) {
        if (!SMPItem.isSMPItem(lastCursorItem.get(e.getPlayer().getUniqueId()))) return;
        if (canHoldTwoWaterBuckets(e.getPlayer())) return;
        e.setCancelled(true);
    }

}
