package com.hyperdondon.lib.item.antipocket;

import com.hyperdondon.lib.item.util.SMPItem;
import lombok.Getter;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;

public final class AntiStore implements Listener {
    @Getter
    private static final AntiStore instance = new AntiStore();

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        if (e.getClickedInventory() != e.getWhoClicked().getInventory()) {
            if (e.getClick() == ClickType.LEFT || e.getClick() == ClickType.RIGHT) {
                if (SMPItem.isSMPItem(e.getWhoClicked().getItemOnCursor())) {
                    e.setCancelled(true);
                    return;
                }
            }

            if (e.getClick() == ClickType.SWAP_OFFHAND) {
                if (SMPItem.isSMPItem(e.getWhoClicked().getInventory().getItemInOffHand())) {
                    e.setCancelled(true);
                    return;
                }
            }


            if (e.getClick() == ClickType.NUMBER_KEY) {
                if (SMPItem.isSMPItem(e.getWhoClicked().getInventory().getItem(e.getHotbarButton()))) {
                    e.setCancelled(true);
                    return;
                }
            }
        } else if (e.getClickedInventory() == e.getWhoClicked().getInventory())
            if (e.getClick() == ClickType.SHIFT_LEFT || e.getClick() == ClickType.SHIFT_RIGHT) {
                if (SMPItem.isSMPItem(e.getWhoClicked().getItemOnCursor())) {
                    e.setCancelled(true);
                    return;
                }
            }
    }


    @EventHandler
    public void onInvDrag(InventoryDragEvent e) {
        //Loop the slots
        for (int i : e.getRawSlots()) {
            //Check if the inventory is the player's opened inventory aka a chest inv.
            if (e.getWhoClicked().getOpenInventory().getTopInventory() != e.getView().getInventory(i)) continue;
            //Check if the item the player was dragging is a marine item.
            if (SMPItem.isSMPItem(e.getOldCursor())) {
                e.setCancelled(true);
                return;
            }
        }
    }
}
