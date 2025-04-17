package com.hyperdondon.lib.powers.cooldown.system;

import com.hyperdondon.lib.powers.Power;
import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.text.Component;

@Getter
@Setter
public class PowerData {
    private Power power;
    private Component display;
    private ActionUse use;

    public PowerData(Power powerarg, Component component, ActionUse actionUse) {
        power = powerarg;
        display = component;
        use = actionUse;
    }
}
