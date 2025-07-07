package com.hyperdondon.lib;

import com.hyperdondon.lib.item.util.ItemConstructor;
import org.mineacademy.fo.plugin.SimplePlugin;

public abstract class SMPPlugin extends SimplePlugin {
    public abstract ItemConstructor getItemConstructor();
}
