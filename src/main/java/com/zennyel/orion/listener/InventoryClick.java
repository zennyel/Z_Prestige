package com.zennyel.orion.listener;

import com.zennyel.orion.other.MessagesConfig;
import com.zennyel.orion.other.PrestigeConfig;
import com.zennyel.orion.prestige.PrestigeManager;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClick implements Listener {

    private PrestigeManager manager;
    private MessagesConfig messagesConfig;
    private PrestigeConfig prestigeConfig;

    public InventoryClick(PrestigeManager manager, MessagesConfig messagesConfig, PrestigeConfig prestigeConfig) {
        this.manager = manager;
        this.messagesConfig = messagesConfig;
        this.prestigeConfig = prestigeConfig;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();

        if(!e.getView().getTitle().equalsIgnoreCase("§8Confirm prestiging!")){
            return;
        }

        switch (e.getRawSlot()){
            case 11:
                if(canPrestige(player)){
                    manager.setIsPrestiging(player, true);
                    player.sendMessage(messagesConfig.getMessage("Messages.prestige", player));
                    player.closeInventory();
                }else {
                    player.sendMessage(messagesConfig.getMessage("Messages.error_insufficient_hearts", player));
                    player.closeInventory();
                }
                break;
            case 15:
                player.sendMessage(messagesConfig.getMessage("Messages.cancel", player));
                player.closeInventory();
                break;
        }
        e.setCancelled(true);
    }

    public boolean canPrestige(Player player){
        int hearts = prestigeConfig.getConfiguration().getInt("Prestige.cost") / 2;
        return player.getHealth() >= hearts;
    }



}
