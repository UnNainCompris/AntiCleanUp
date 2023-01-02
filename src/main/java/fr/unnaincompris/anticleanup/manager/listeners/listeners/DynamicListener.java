package fr.unnaincompris.anticleanup.manager.listeners.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;

public class DynamicListener implements Listener {

    @EventHandler
    public void onArmorStand(PlayerArmorStandManipulateEvent event) {
        if(!event.getRightClicked().isVisible() && !event.getRightClicked().hasGravity()) event.setCancelled(true);
    }
}
