package fr.unnaincompris.anticleanup.manager.anticleanup.listeners;

import de.tr7zw.nbtapi.NBTItem;
import fr.unnaincompris.anticleanup.Main;
import fr.unnaincompris.anticleanup.manager.anticleanup.AntiCleanupManager;
import fr.unnaincompris.anticleanup.utils.ColorUtils;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class PlayerDamageListener implements Listener {

    private final AntiCleanupManager manager;

    public PlayerDamageListener(AntiCleanupManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onPlayerGetKilled(PlayerDeathEvent event) {

        if(event.getEntity().getKiller() == null) return;
        final Player victim = event.getEntity();
        final Player killer = victim.getKiller();

        final UUID killKey = UUID.randomUUID();
        for (int i = 0 ; i < event.getDrops().size() ; i++) {
            final NBTItem item = new NBTItem(event.getDrops().get(i));

            item.setString("allowedPlayer", killer.getUniqueId().toString());
            item.setString("killKey", killKey.toString());

            manager.addItem(killKey);

            event.getDrops().set(i, item.getItem());
        }

        final ArmorStand armorStand = (ArmorStand) victim.getWorld().spawnEntity(victim.getLocation(), EntityType.ARMOR_STAND);
        armorStand.setVisible(false); armorStand.setGravity(false); armorStand.setCanPickupItems(false); armorStand.setCustomNameVisible(true);
        manager.armorStandList.add(armorStand);
        new BukkitRunnable() {
            final UUID key = killKey;
            int counter = 30; // time in second
            final String killerName = killer.getName();
            public void run() {
                if(counter == 0 || !manager.containKey(key)) {
                    if(manager.containKey(key))
                        manager.removeKey(key);
                    armorStand.remove();
                    this.cancel();
                } else {
                    armorStand.setCustomName(ColorUtils.translate("&7Stuff réservé a " + killerName + " durant &6" + counter + " &7secondes"));
                    counter--;
                }
            }
        }.runTaskTimer(Main.getInstance(), 0, 20);

    }

}
