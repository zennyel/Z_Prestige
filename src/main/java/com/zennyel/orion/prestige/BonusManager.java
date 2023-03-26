package com.zennyel.orion.prestige;

import com.zennyel.orion.other.PrestigeConfig;

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
         for(int i = 0; i < config.getConfiguration().getStringList("Prestige.level").size(); i++){
             command = config.getConfiguration().getString("Prestige.level." + i);
             if(command == null){
                 return;
             }
             commands.add(command);
         }
     }

     public void executeCommands(int level){
         addConfigs();
         commands.get(level -1);
     }

    public List<String> getCommands() {
        return commands;
    }
}
