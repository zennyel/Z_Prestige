package com.zennyel.orion;

import com.zennyel.orion.commands.PrestigeCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class Z_Prestige extends JavaPlugin {

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

    public void registerCommands(){
        getCommand("prestige").setExecutor(new PrestigeCommand());
    }

}
