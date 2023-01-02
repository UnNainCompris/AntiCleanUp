package fr.unnaincompris.anticleanup;

import fr.unnaincompris.anticleanup.manager.anticleanup.AntiCleanupManager;
import fr.unnaincompris.anticleanup.manager.listeners.ListenersManager;
import fr.unnaincompris.anticleanup.utils.Manager;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Getter
    private static Main instance;

    @Getter private ListenersManager listenersManager;
    @Getter private AntiCleanupManager playerManager;

    @Override
    public void onEnable() {
        instance = this;
        Manager.Enabler.init(this);
    }

    @Override
    public void onDisable() {
        Manager.Enabler.stop(this);
    }
}
