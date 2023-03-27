package com.zennyel.orion.prestige;

import com.zennyel.orion.Z_Prestige;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class PrestigePlaceHolderExpansion extends PlaceholderExpansion {

    private final Z_Prestige plugin;
    PrestigeManager manager;
    public PrestigePlaceHolderExpansion(Z_Prestige plugin, PrestigeManager manager) {
        this.plugin = plugin;
        this.manager = manager;
    }

    @Override
    public String getIdentifier() {
        return "prestige_lifesteal";
    }

    @Override
    public  String getAuthor() {
        return "Zennyel";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        if(params.equalsIgnoreCase("prestige_lifesteal")){
            if(player != null)
            return String.valueOf(manager.getPrestige((Player) player).getPrestige());
        }
        return null;
    }
}
