package com.hyperdondon.lib.powers.cooldown.system;

import com.hyperdondon.lib.item.SMPItem;
import com.hyperdondon.lib.powers.Power;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.Common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

@Getter
public class PowerSelectionSystem implements Listener {
    private static final PowerSelectionSystem instance = new PowerSelectionSystem();
    private HashMap<UUID, Integer> selectors = new HashMap<>();
    private ArrayList<PowerData> powers = new ArrayList();
    private ActionUse use;
    private boolean onlyUseOnCrouch;
    private boolean noUseOnCrouch;

    public void initialize(boolean onlyUseOnCroucharg, boolean noUseOnCroucharg, ActionUse usearg) {
        Common.registerEvents(instance);
        onlyUseOnCrouch = onlyUseOnCroucharg;
        noUseOnCrouch = noUseOnCroucharg;
        use = usearg;
    }

    public void registerPower(PowerData power) {
        powers.add(power);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (onlyUseOnCrouch)
            if (!event.getPlayer().isSneaking()) return;
        if (event.getHand() != EquipmentSlot.HAND) return; // Main hand only, don't execute twice
        if (!event.getAction().toString().contains(use.toString() + "_CLICK")) return;

        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        if (!SMPItem.isSMPItem(item)) return;

        int selected;
        selected = selectors.getOrDefault(player.getUniqueId(), 1);
        if (selected == 3) selected = 1;
        else selected++;
        set(player.getUniqueId(), selected);
        player.playSound(player.getLocation(), Sound.ENTITY_GOAT_MILK, SoundCategory.MASTER, 1F, 0.5F);
        player.playSound(player.getLocation(), Sound.ITEM_SHOVEL_FLATTEN, SoundCategory.MASTER, 1F, 1F);
    }

    public void set(UUID id, int sel) {
        if (selectors.containsKey(id)) selectors.replace(id, sel);
        else selectors.put(id, sel);
    }
}
