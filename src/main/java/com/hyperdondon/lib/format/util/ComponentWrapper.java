package com.hyperdondon.lib.format.util;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class ComponentWrapper {
    @Getter
    private final Component component;

    public ComponentWrapper(String text) {
        component = deserialize(text);
    }

    public ComponentWrapper(String... texts) {
        this.component = deserialize(String.join("\n&r", texts));
    }

    public static Component deserialize(String text) {
        text = ChatColor.translateAlternateColorCodes('&', text); //ampersand coloring is dope
        text = MiniMessage.miniMessage().serialize(LegacyComponentSerializer.legacySection().deserialize(text)); //turn the text into a component, then turn it into minimessage
        if (text.startsWith("\\"))
            text = text.substring(1); //for whatever reason, the text will have a blackslash at the start and removing it makes it work??
        return miniMessage(text.replace("\\", "")); //finally, make it a component
    }

    public static Component miniMessage(String text) {
        return MiniMessage.miniMessage().deserialize(text);
    }

    public static Component miniMessage(String... texts) {
        return miniMessage(String.join("\n", texts));
    }

    public String toSectionSign() {
        return LegacyComponentSerializer.legacySection().serialize(component);
    }

    public String toAmpersand() {
        return LegacyComponentSerializer.legacyAmpersand().serialize(component);
    }

    public List<Component> splitLines() {
        List<Component> lines = new ArrayList<>();
        String serialized = LegacyComponentSerializer.legacySection().serialize(component);

        for (String line : serialized.split("\n")) {
            lines.add(LegacyComponentSerializer.legacySection().deserialize(line));
        }

        return lines;
    }
}
