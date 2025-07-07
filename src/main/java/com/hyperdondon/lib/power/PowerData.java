package com.hyperdondon.lib.power;

import com.hyperdondon.lib.item.util.SMPItem;
import com.hyperdondon.lib.power.system.ActionUse;
import com.hyperdondon.lib.util.format.ComponentWrapper;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PowerData {
    private Power power;
    private ComponentWrapper display;
    private ActionUse use;
    private SMPItem<?> smpItem;

    public PowerData(Power pPower, ComponentWrapper pComponent, ActionUse pActionUse) {
        power = pPower;
        display = pComponent;
        use = pActionUse;
        smpItem = null;
    }

    public PowerData(Power pPower, ComponentWrapper pComponent, ActionUse pActionUse, SMPItem<?> pItem) {
        power = pPower;
        display = pComponent;
        use = pActionUse;
        smpItem = pItem;
    }

    public boolean hasItem() {
        return smpItem != null;
    }
}
