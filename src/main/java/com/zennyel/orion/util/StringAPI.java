package com.zennyel.orion.util;

import com.zennyel.orion.Z_Prestige;
import org.bukkit.configuration.file.YamlConfiguration;

public class StringAPI{

    private String path;
    private YamlConfiguration configuration;
    private Z_Prestige instance;

    public StringAPI(YamlConfiguration configuration) {
        this.configuration = configuration;
        this.instance = Z_Prestige.getPlugin(Z_Prestige.class);
    }

    public void getString(String file){
        configuration.getString(instance.getDataFolder() + file + ".yml");
    }

}
