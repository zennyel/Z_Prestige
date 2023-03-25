package com.zennyel.orion.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class ItemAPI extends ItemStack {

    public ItemAPI(Material material, String displayName, String lore){
        setType(material);
        ItemMeta im = getItemMeta();
        im.setDisplayName(displayName);
        im.setLore(Arrays.asList(lore));
        setItemMeta(im);
    }
    public ItemAPI(Material material, String displayName, List<String> lore){
        setType(material);
        ItemMeta im = getItemMeta();
        im.setDisplayName(displayName);
        im.setLore(lore);
        setItemMeta(im);
    }

}
