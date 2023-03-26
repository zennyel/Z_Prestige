package com.zennyel.orion.prestige;

import com.zennyel.orion.other.PrestigeConfig;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class BonusManager {


    private PrestigeConfig config;
     private int lastLevel;
     private String command;
     private List<String> commands;

     public BonusManager(PrestigeConfig config){
         this.config = config;
     }
     public void addConfigs(){
         for(int i = 0; i < config.getConfiguration().getConfigurationSection("Prestige.level").getKeys(true).size(); i++){
             command = config.getConfiguration().getString("Prestige.level." + i);
             if(command == null){
                 int previous = i -1;
                 command = config.getConfiguration().getString("Prestige.level." + previous);
                 commands.add(command);
                 return;
             }
             commands.add(command);
         }
     }

     public void executeListCommand(int level, Player player){
         addConfigs();
         command = commands.get(level -1).replace("&", "§").replace("{player}", player.getName());
         ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
         Bukkit.dispatchCommand(console, command);
     }

    public List<String> getCommands() {
        return commands;
    }
}
