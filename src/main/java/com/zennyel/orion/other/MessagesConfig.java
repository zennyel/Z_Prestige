package com.zennyel.orion.other;

import com.zennyel.orion.Z_Prestige;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

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
        stream = new InputStreamReader(instance.getResource("messages.yml"), StandardCharsets.UTF_8);
        this.configFile = new File(instance.getDataFolder() + "messages.yml");
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

    public File getConfigFile() {
        return configFile;
    }

    public YamlConfiguration getDefaultConfig() {
        return defaultConfig;
    }
}
