package com.zennyel.orion.menu;

import com.zennyel.orion.util.ItemAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class PrestigeGUI {

    Inventory inventory;

    public PrestigeGUI(Inventory inventory, Player player){
        this.inventory = inventory;
        addItems(player);
    }

    private void addItems(Player player){
        inventory.setItem(9 + 2, new ItemAPI(Material.NETHER_STAR, "§6Prestige", ""));
        inventory.setItem(9 + 5, playerHead(player));
        inventory.setItem(9 + 7, new ItemAPI(Material.BEACON, "§bBonus!", "bonus for prestiging"));
        addGlasses();
        player.openInventory(inventory);
    }


    public void addPrestige(){

    }

    public void addGlasses(){
        for(int i = 0; i < inventory.getSize(); i++){
            Material pane = Material.STAINED_GLASS_PANE;
            inventory.setItem(i, new ItemAPI(pane, "", ""));
        }
    }

    public ItemAPI playerHead(Player player){
        Material material = Material.SKULL;
        ItemAPI api = new ItemAPI(material, ""+player.getName(), "");
        SkullMeta meta = (SkullMeta) api.getItemMeta();
        meta.setOwningPlayer(Bukkit.getOfflinePlayer(player.getUniqueId()));
        api.setItemMeta(meta);
        return api;
    }



}
