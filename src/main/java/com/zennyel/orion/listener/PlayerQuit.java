package com.zennyel.orion.listener;

import com.zennyel.orion.managers.PrestigeManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {
    private PrestigeManager manager;
    public PlayerQuit(PrestigeManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player p = e.getPlayer();
        if(manager.getPrestige(p) == null){
            manager.loadPrestige(p);
        }
        manager.savePrestige(p);
    }
}
