package com.zennyel.orion.prestige;

import com.zennyel.orion.other.PrestigeConfig;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class BonusManager {


    private PrestigeConfig configPrestige;
    private FileConfiguration config;
    private PrestigeManager manager;

     String command;
     List<String> commands;

     public BonusManager(PrestigeConfig config, PrestigeManager manager){
         this.configPrestige = config;
         this.config = configPrestige.getConfiguration();
         this.manager = manager;
     }

    public void executeCommand(Player p, int level) {
        List<String> commands = new ArrayList<>();

        while (level > 0) {
            if (config.contains("Prestige.level." + level)) {
                commands = config.getStringList("Prestige.level." + level);
                break;
            }
            level--;
        }


        if (commands.isEmpty()) {
            while (level > 0) {
                level--;
                if (config.contains("Prestige.level." + level)) {
                    commands = config.getStringList("Prestige.level." + level);
                    break;
                }
            }
        }

        ConsoleCommandSender console = Bukkit.getConsoleSender();
        for (String command : commands) {
            command = command.replace("{player}", p.getName())
                    .replace("{prestige}", String.valueOf(manager.getPrestige(p).getPrestige()));
            Bukkit.dispatchCommand(console, command);
        }
    }

    public List<String> getCommands() {
        return commands;
    }
}
