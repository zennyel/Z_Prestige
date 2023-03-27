package com.zennyel.orion.menu;

import com.zennyel.orion.Z_Prestige;
import com.zennyel.orion.other.MessagesConfig;
import com.zennyel.orion.other.PrestigeConfig;
import com.zennyel.orion.prestige.PrestigeManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrestigeGUI {

    private final Inventory inventory;
    private Z_Prestige instance = Z_Prestige.getPlugin(Z_Prestige.class);
    private PrestigeManager manager = instance.getPrestigeManager();
    private PrestigeConfig prestigeConfig = instance.getPrestigeConfig();

    public PrestigeGUI(Inventory inventory, Player player){
        this.inventory = inventory;
        addItems(player);
    }

    private void addItems(Player player){
        addGlasses();
        inventory.setItem(11, item(Material.EMERALD_BLOCK, "§a§lCONFIRM PRESTIGE", prestigeConfirmMessage(player)));
        inventory.setItem(15, item(Material.REDSTONE_BLOCK, "§c§lCANCEL", "§e→ exit menu and cancel!"));
        player.openInventory(inventory);
    }

    public void addGlasses(){
        for(int i = 0; i < inventory.getSize(); i++){
            Material pane = Material.STAINED_GLASS_PANE;
            inventory.setItem(i, item(pane, "*", ""));
        }
    }

    public ItemStack item(Material material, String displayname, String lore){
        ItemStack is = new ItemStack(material);
        ItemMeta isMeta = is.getItemMeta();
        isMeta.setDisplayName(displayname);
        isMeta.setLore(Arrays.asList(lore));
        is.setItemMeta(isMeta);
        return is;
    }

    public ItemStack item(Material material, String displayname, List<String> lore){
        ItemStack is = new ItemStack(material);
        ItemMeta isMeta = is.getItemMeta();
        isMeta.setDisplayName(displayname);
        isMeta.setLore(lore);
        is.setItemMeta(isMeta);
        return is;
    }

    public List<String> prestigeConfirmMessage(Player player){
        int cost = prestigeConfig.getConfiguration().getInt("Prestige.cost");
        int remaining = prestigeConfig.getConfiguration().getInt("Prestige.remaining");
        List<String> rewards = prestigeConfig.getConfiguration().getStringList("Prestige.rewards");
        List<String> lore = new ArrayList<>();
        lore.add("§7Click for confirm prestiging");
        lore.add("§7prestiged times: " + manager.getPrestige(player).getPrestige());
        lore.add("");
        lore.add("§fPrestiging costs §c" + cost + " HEARTS§f but gives");
        lore.add("§fyou Keys§f and a §bPrefix§f. It also makes");
        lore.add("§fyou stronger and more impressive!");
        lore.add("");
        lore.add("§b§lREWARDS:");
        lore.add(rewards.get(0).replace("&", "§"));
        lore.add(rewards.get(1).replace("&", "§"));
        lore.add("");
        lore.add("§4§lWARNING: §cYou will be  set back to §c" + remaining + " hearts.");
        lore.add("§cbe sure that you want to do this.");
        return lore;
    }



}
