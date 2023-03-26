package com.zennyel.orion.prestige;

import com.zennyel.orion.database.MySQL;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class PrestigeManager {

    private MySQL sql;
    private HashMap<Player, Prestige> prestigeHashMap;
    private HashMap<Player, Boolean> isPrestiging;

    public PrestigeManager(MySQL sql){
        this.sql = sql;
    }

    public void savePrestige(Player player){
        String uuid = player.getUniqueId().toString();
        sql.setPrestige(uuid, getPrestige(player).getPrestige());
    }

    public void loadPrestige(Player player){

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
