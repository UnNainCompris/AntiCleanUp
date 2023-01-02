package fr.unnaincompris.anticleanup.utils;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;

import java.lang.reflect.Field;

public class NmsUtils {
    public static CommandMap getCommandMap() {
        try {
            CraftServer craftServer = ((CraftServer) Bukkit.getServer());

            Field commandMapField = craftServer.getClass().getDeclaredField("commandMap");
            commandMapField.setAccessible(true);

            return (CommandMap) commandMapField.get(craftServer);
        } catch(NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
