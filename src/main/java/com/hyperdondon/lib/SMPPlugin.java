package com.hyperdondon.lib;

import com.hyperdondon.lib.powers.cooldown.system.PowerSelectionSystem;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.mineacademy.fo.plugin.SimplePlugin;

import java.util.Objects;

public abstract class SMPPlugin extends SimplePlugin {

    private static SMPPlugin instance;

    public static SMPPlugin getInstance() {
        if (instance == null) {
            try {
                instance = (SMPPlugin) JavaPlugin.getPlugin(SMPPlugin.class);
            } catch (IllegalStateException ex) {
                if (Bukkit.getPluginManager().getPlugin("PlugMan") != null) {
                    Bukkit.getLogger().severe("Failed to get instance of the plugin, if you reloaded using PlugMan, you need to do a clean restart instead.");
                }

                throw ex;
            }

            Objects.requireNonNull(instance, "Cannot get a new instance! Have you reloaded?");
        }

        return instance;
    }

    public Component MiniMessage(String text) {return MiniMessage.miniMessage().deserialize(text);}

    public Component MiniMessage(String... texts) {return MiniMessage(String.join("\n", texts));}

    public String Serialize(Component component) {return LegacyComponentSerializer.legacySection().serialize(component);}

    public String Serialize(Component... components) {
        String[] texts = new String[components.length];
        for (int i = 0; i < components.length; i++) texts[i] = Serialize(components[i]);
        return String.join("\n", texts);
    }

    public final void onReloadablesStart() {
        this.onPluginEnable();
    }

    protected abstract void onPluginEnable();
}
