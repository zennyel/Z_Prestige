package com.zennyel.orion.config;

import com.zennyel.orion.Z_Prestige;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

public class PrestigeConfig {

    private File configFile;
    private FileConfiguration configuration;
    private Reader stream;
    private YamlConfiguration defaultConfig;

    public  PrestigeConfig(Z_Prestige instance){
        this.configFile = new File(instance.getDataFolder(), "prestige.yml");
        if (!configFile.exists()) {
            try {
                instance.saveResource("prestige.yml", false);
            } catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            }
        }
        this.configuration = YamlConfiguration.loadConfiguration(configFile);
        this.defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(instance.getResource("messages.yml"), StandardCharsets.UTF_8));
        configuration.setDefaults(defaultConfig);
    }

    public String getPrestige(String path, Player player){
        return configuration.getString(path).replace("&", "§").replace("{player}", player.getName());
    }

    public FileConfiguration getConfiguration() {
        return configuration;
    }

    public YamlConfiguration getDefaultConfig() {
        return defaultConfig;
    }
}
