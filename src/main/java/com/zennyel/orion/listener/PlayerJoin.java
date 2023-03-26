package com.zennyel.orion.listener;

import com.zennyel.orion.prestige.PrestigeManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener{
    private PrestigeManager manager;

    public PlayerJoin(PrestigeManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player  p = e.getPlayer();

    }
}
