package com.zennyel.orion.other;

import com.zennyel.orion.Z_Prestige;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

public class PrestigeConfig {

    private File configFile;
    private FileConfiguration configuration;
    private Reader stream;
    private YamlConfiguration defaultConfig;

    public  PrestigeConfig(Z_Prestige instance){
        stream = new InputStreamReader(instance.getResource("prestige.yml"), StandardCharsets.UTF_8);
        this.configFile = new File(instance.getDataFolder() + "prestige.yml");
        configuration = YamlConfiguration.loadConfiguration(configFile);
        defaultConfig = YamlConfiguration.loadConfiguration(stream);
        if(!configFile.exists()){
            try {
                configFile.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        configuration.setDefaults(defaultConfig);
    }

    public FileConfiguration getConfiguration() {
        return configuration;
    }

    public YamlConfiguration getDefaultConfig() {
        return defaultConfig;
    }
}
