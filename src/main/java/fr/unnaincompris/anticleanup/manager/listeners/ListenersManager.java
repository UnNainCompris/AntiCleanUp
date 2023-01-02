package fr.unnaincompris.anticleanup.manager.listeners;

import fr.unnaincompris.anticleanup.Main;
import fr.unnaincompris.anticleanup.manager.listeners.listeners.DynamicListener;
import fr.unnaincompris.anticleanup.utils.Manager;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public class ListenersManager implements Manager {

    final List<Listener> listenerList = new ArrayList<>();

    public ListenersManager() {
        listenerList.add(new DynamicListener());

        registerListeners();
    }

    private void registerListeners() {
        listenerList.forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, Main.getInstance()));
    }

}
