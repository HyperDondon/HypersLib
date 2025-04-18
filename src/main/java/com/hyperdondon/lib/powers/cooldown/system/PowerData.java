package com.hyperdondon.lib.powers.cooldown.system;

import com.hyperdondon.lib.powers.Power;
import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.text.Component;
import org.bukkit.inventory.ItemStack;

@Getter
@Setter
public class PowerData {
    private Power power;
    private Component display;
    private ActionUse use;
    private String itemid;

    public PowerData(Power powerarg, Component component, ActionUse actionUse) {
        power = powerarg;
        display = component;
        use = actionUse;
        itemid = null;
    }

    public PowerData(Power powerarg, Component component, ActionUse actionUse, String itemarg) {
        power = powerarg;
        display = component;
        use = actionUse;
        itemid = itemarg;
    }

    public boolean hasItem() {
        return itemid != null;
    }
}
