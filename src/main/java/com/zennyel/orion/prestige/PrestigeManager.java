package com.zennyel.orion.prestige;

import com.zennyel.orion.database.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class PrestigeManager {

    private MySQL sql;
    private HashMap<Player, Prestige> prestigeHashMap;
    private HashMap<Player, Boolean> isPrestiging;
    private FileConfiguration config;

    public PrestigeManager(MySQL sql, FileConfiguration config){
        this.config = config;
        this.sql = sql;
    }

    public void savePrestige(Player player){
        String uuid = player.getUniqueId().toString();
        int prestige = getPrestige(player).getPrestige();
        if(config.getBoolean("Config.logMessages")) {
            Bukkit.getConsoleSender().sendMessage("§bSaving " +
                    player.getName() +
                    " to database with " +
                    prestige +
                    " prestige levels!");
        }
        sql.setPrestige(uuid, prestige);
    }

    public void loadPrestige(Player player){
        prestigeHashMap.put(player, new Prestige(sql.getPrestige(player.getUniqueId().toString())));
    }

    public Prestige getPrestige(Player player){
        return prestigeHashMap.get(player);
    }

    public void setPrestige(Player player, Prestige prestige){
        prestigeHashMap.put(player, prestige);
    }

    public boolean isPrestiging(Player player){
        return this.isPrestiging.get(player);
    }

    public void setIsPrestiging(Player player, Boolean isPrestiging ){
        this.isPrestiging.put(player, isPrestiging);
    }


}
