package fr.unnaincompris.anticleanup.manager.anticleanup.listeners;

import de.tr7zw.nbtapi.NBTItem;
import fr.unnaincompris.anticleanup.manager.anticleanup.AntiCleanupManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import java.util.UUID;

public class PickupItemListener implements Listener {

    private final AntiCleanupManager manager;

    public PickupItemListener(AntiCleanupManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onPlayerPickupItem(PlayerPickupItemEvent event) {
        final NBTItem item = new NBTItem(event.getItem().getItemStack());
        if(!item.hasKey("allowedPlayer")) return;
        if(!item.getString("allowedPlayer").equals(event.getPlayer().getUniqueId().toString()) && manager.containKey(UUID.fromString(item.getString("killKey")))) {
            event.setCancelled(true);
            return;
        }
        item.removeKey("allowedPlayer");

        manager.removeItem(UUID.fromString(item.getString("killKey")));
        item.removeKey("killKey");
        event.getItem().setItemStack(item.getItem());
    }

    @EventHandler
    public void onBlockPickupItem(InventoryPickupItemEvent event) {
        final NBTItem item = new NBTItem(event.getItem().getItemStack());
        if(item.hasKey("allowedPlayer")) event.setCancelled(true);
    }
}