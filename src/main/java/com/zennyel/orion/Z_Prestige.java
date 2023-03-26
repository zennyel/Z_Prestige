package com.zennyel.orion;

import com.zennyel.orion.commands.PrestigeCommand;
import com.zennyel.orion.database.MySQL;
import com.zennyel.orion.listener.InventoryClick;
import com.zennyel.orion.listener.PlayerJoin;
import com.zennyel.orion.listener.PlayerQuit;
import com.zennyel.orion.other.MessagesConfig;
import com.zennyel.orion.other.PrestigeConfig;
import com.zennyel.orion.prestige.BonusManager;
import com.zennyel.orion.prestige.PrestigeManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Z_Prestige extends JavaPlugin {
    private MySQL sql;
    private MessagesConfig messagesConfig;
    private PrestigeConfig prestigeConfig;
    private BonusManager bonusManager;
    private PrestigeManager prestigeManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        setupSql();
        setupManagers();
        registerEvents();
        registerCommands();
    }

    @Override
    public void onDisable() {
        sql.disconnect();
    }
    private void setupSql(){
        this.sql = new MySQL(this.getConfig());
        sql.connect();
        sql.createTable();
    }
    private void setupManagers(){
        this.messagesConfig = new MessagesConfig(this);
        this.prestigeConfig = new PrestigeConfig(this);
        this.bonusManager = new BonusManager(prestigeConfig);
        this.prestigeManager = new PrestigeManager(sql, messagesConfig.getConfiguration());
    }

    public void registerCommands(){
        getCommand("prestige").setExecutor(new PrestigeCommand());
    }
    public void registerEvents(){
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new InventoryClick(prestigeManager, messagesConfig), this);
        pm.registerEvents(new PlayerQuit(prestigeManager), this);
        pm.registerEvents(new PlayerJoin(prestigeManager), this);
    }

}
