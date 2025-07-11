package com.hyperdondon.lib;

import org.mineacademy.fo.plugin.SimplePlugin;
import org.mineacademy.fo.remain.Remain;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Pattern;

public final class KotlinObjectAutoRegisterScanner {
    public static List<Class<?>> findValidClasses() {
        final List<Class<?>> classes = new ArrayList<>();

        // Ignore anonymous inner classes
        final Pattern anonymousClassPattern = Pattern.compile("\\w+\\$[0-9]$");

        try (final JarFile file = new JarFile(SimplePlugin.getSource())) {
            for (final Enumeration<JarEntry> entry = file.entries(); entry.hasMoreElements(); ) {
                final JarEntry jar = entry.nextElement();
                final String name = jar.getName().replace("/", ".");

                // Ignore files such as settings.yml
                if (!name.endsWith(".class"))
                    continue;

                final String className = name.substring(0, name.length() - 6);
                Class<?> clazz = null;

                // Look up the Java class, silently ignore if failing
                try {
                    clazz = SimplePlugin.class.getClassLoader().loadClass(className);

                } catch (final ClassFormatError | VerifyError | NoClassDefFoundError | ClassNotFoundException |
                               IncompatibleClassChangeError error) {
                    continue;
                }

                // Ignore abstract or anonymous classes
                if (!Modifier.isAbstract(clazz.getModifiers()) && !anonymousClassPattern.matcher(className).find())
                    classes.add(clazz);
            }

        } catch (final Throwable t) {
            Remain.sneaky(t);
        }

        return classes;
    }

}
