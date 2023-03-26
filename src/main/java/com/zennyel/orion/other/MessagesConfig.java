package com.zennyel.orion.other;

import com.zennyel.orion.Z_Prestige;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

public class MessagesConfig {

    private File configFile;
    private FileConfiguration configuration;
    private Reader stream;
    private YamlConfiguration defaultConfig;

    public MessagesConfig(Z_Prestige instance){
        this.configFile = new File(instance.getDataFolder(), "messages.yml");
        if (!configFile.exists()) {
            try {
                instance.saveResource("messages.yml", false);
            } catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            }
        }
        this.configuration = YamlConfiguration.loadConfiguration(configFile);
        this.defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(instance.getResource("messages.yml"), StandardCharsets.UTF_8));
        configuration.setDefaults(defaultConfig);
    }


    public String getMessage(String path, Player player){
        return configuration.getString(path)
                .replace("&", "§")
                .replace("{player}", player.getName())
                .replace("{tag}", getConfiguration()
                        .getString("Config.tag").replace("&", "§"));
}

    public FileConfiguration getConfiguration() {
        return configuration;
    }

    public File getConfigFile() {
        return configFile;
    }

    public YamlConfiguration getDefaultConfig() {
        return defaultConfig;
    }
}
