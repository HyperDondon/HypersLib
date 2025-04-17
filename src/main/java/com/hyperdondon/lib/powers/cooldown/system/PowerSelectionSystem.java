package com.hyperdondon.lib.powers.cooldown.system;

import com.hyperdondon.lib.SMPPlugin;
import com.hyperdondon.lib.powers.Power;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.JoinConfiguration;
import org.bukkit.event.Listener;
import org.mineacademy.fo.Common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

@Getter
public class PowerSelectionSystem implements Listener {
    private static final PowerSelectionSystem instance = new PowerSelectionSystem();
    private HashMap<UUID, Integer> selectors = new HashMap<>();
    private ArrayList<PowerData> powers = new ArrayList();
    private boolean onlyUseOnCrouch;
    private boolean noUseOnCrouch;

    public void initialize(boolean onlyUseOnCroucharg, boolean noUseOnCroucharg) {
        Common.registerEvents(instance);
        onlyUseOnCrouch = onlyUseOnCroucharg;
        noUseOnCrouch = noUseOnCroucharg;
    }

    public void registerPower(Power power, Component display, ActionUse actionUse) {
        powers.add(new PowerData(power, display, actionUse));
    }
}
