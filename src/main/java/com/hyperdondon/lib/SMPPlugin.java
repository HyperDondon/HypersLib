package com.hyperdondon.lib;

import com.hyperdondon.lib.annotation.KotlinObjectAutoRegister;
import com.hyperdondon.lib.item.util.ItemConstructor;
import org.bukkit.event.Listener;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.plugin.SimplePlugin;

public abstract class SMPPlugin extends SimplePlugin {
    public abstract ItemConstructor getItemConstructor();

    protected abstract void onSMPPluginStart();

    public void onPluginStart() {
        onSMPPluginStart();
        registerKotlinObjectListeners();
    }

    public void registerKotlinObjectListeners() {
        for (Class<?> clazz : KotlinObjectAutoRegisterScanner.findValidClasses()) {
            if (!clazz.isAnnotationPresent(KotlinObjectAutoRegister.class)) continue;

            try {
                var field = clazz.getDeclaredField("INSTANCE");
                field.setAccessible(true);

                Object instance = field.get(null);

                if (!(instance instanceof Listener)) {
                    Common.log("Class " + clazz + " doesn't implement Listener!");
                    continue;
                }

                Common.registerEvents((Listener) instance);
            } catch (Exception e) {
                Common.error(e, "Unable to register Kotlin object %s", clazz.getName());
            }
        }
    }
}
