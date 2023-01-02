package fr.unnaincompris.anticleanup.manager.anticleanup;

import fr.unnaincompris.anticleanup.Main;
import fr.unnaincompris.anticleanup.manager.anticleanup.listeners.PickupItemListener;
import fr.unnaincompris.anticleanup.manager.anticleanup.listeners.PlayerDamageListener;
import fr.unnaincompris.anticleanup.utils.Manager;
import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.PluginManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class AntiCleanupManager implements Manager {
    /**
     * Use to store the item drop at kill and get if need to show the holograms
     */
    private final HashMap<UUID, Integer> killKeyItemMap = new HashMap<>();

    /**
     * Use to kill all armor stand on plugin stop
     */
    public final List<ArmorStand> armorStandList = new ArrayList<>();

    public AntiCleanupManager() {
        registerListener();
    }

    public void stop() {
        armorStandList.forEach(Entity::remove);
    }

    /**
     * Use to register all listeners need for the anticleanup system
     */
    public void registerListener() {
        final PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new PlayerDamageListener(this), Main.getInstance());
        pluginManager.registerEvents(new PickupItemListener(this), Main.getInstance());
    }

    public void removeItem(UUID key) {
        if(!killKeyItemMap.containsKey(key)) return;

        if(killKeyItemMap.get(key) == 1) {
            killKeyItemMap.remove(key);
        } else {
            killKeyItemMap.put(key, killKeyItemMap.get(key) - 1);
        }
    }

    public void addItem(UUID key) {
        if(!killKeyItemMap.containsKey(key)) killKeyItemMap.put(key, 1);
        else killKeyItemMap.put(key, killKeyItemMap.get(key) + 1);
    }

    public boolean containKey(UUID key) {
        return killKeyItemMap.containsKey(key);
    }

    public void removeKey(UUID key) {
        killKeyItemMap.remove(key);
    }
}
