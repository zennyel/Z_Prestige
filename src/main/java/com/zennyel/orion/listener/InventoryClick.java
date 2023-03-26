package com.zennyel.orion.listener;

import com.zennyel.orion.other.MessagesConfig;
import com.zennyel.orion.prestige.PrestigeManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClick implements Listener {

    private PrestigeManager manager;
    private MessagesConfig messagesConfig;

    public InventoryClick(PrestigeManager manager, MessagesConfig messagesConfig) {
        this.messagesConfig = messagesConfig;
        this.manager = manager;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();

        if(!e.getInventory().getName().equalsIgnoreCase("§6Prestige Menu")){
            return;
        }

        switch (e.getRawSlot()){
            case 11:
                if(canPrestige(player)){
                    manager.setIsPrestiging(player, true);
                    player.sendMessage(messagesConfig.getMessage("Messages.prestige", player));
                }
                break;
            case 13:
                break;
            case 15:
                break;
        }
        e.setCancelled(true);
    }

    public boolean canPrestige(Player player){
        return player.getHealth() == 20;
    }



}
