package com.hyperdondon.lib.item.antipocket;

import com.hyperdondon.lib.item.util.SMPItem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.mineacademy.fo.annotation.AutoRegister;

@AutoRegister
public final class AntiStore implements Listener {
    @EventHandler
    public void onInvClick(InventoryClickEvent event) {
        if (event.getClickedInventory() != event.getWhoClicked().getInventory()) {
            if (event.getClick() == ClickType.LEFT || event.getClick() == ClickType.RIGHT) {
                if (SMPItem.isSMPItem(event.getCurrentItem())) {
                    event.setCancelled(true);
                    return;
                }
            }

            if (event.getClick() == ClickType.SWAP_OFFHAND) {
                if (SMPItem.isSMPItem(event.getWhoClicked().getInventory().getItemInOffHand())) {
                    event.setCancelled(true);
                    return;
                }
            }

            if (event.getClick() == ClickType.NUMBER_KEY) {
                if (SMPItem.isSMPItem(event.getWhoClicked().getInventory().getItem(event.getHotbarButton()))) {
                    event.setCancelled(true);
                    return;
                }
            }
        } else if (event.getClickedInventory() == event.getWhoClicked().getInventory())
            if (event.getClick() == ClickType.SHIFT_LEFT || event.getClick() == ClickType.SHIFT_RIGHT) {
                if (SMPItem.isSMPItem(event.getCurrentItem())) {
                    event.setCancelled(true);
                    return;
                }
            }
    }


    @EventHandler
    public void onInvDrag(InventoryDragEvent event) {
        //Loop the slots
        for (int i : event.getRawSlots()) {
            //Check if the inventory is the player's opened inventory aka a chest inv.
            if (event.getWhoClicked().getOpenInventory().getTopInventory() != event.getView().getInventory(i)) continue;
            //Check if the item the player was dragging is a marine item.
            if (SMPItem.isSMPItem(event.getOldCursor())) {
                event.setCancelled(true);
                return;
            }
        }
    }
}
