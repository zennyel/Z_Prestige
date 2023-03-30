package com.zennyel.orion;

import com.zennyel.orion.commands.PrestigeCommand;
import com.zennyel.orion.database.MySQL;
import com.zennyel.orion.database.SQLite;
import com.zennyel.orion.listener.InventoryClick;
import com.zennyel.orion.listener.PlayerJoin;
import com.zennyel.orion.listener.PlayerQuit;
import com.zennyel.orion.config.MessagesConfig;
import com.zennyel.orion.config.PrestigeConfig;
import com.zennyel.orion.managers.BonusManager;
import com.zennyel.orion.managers.PrestigeManager;
import com.zennyel.orion.prestige.PrestigePlaceHolderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Z_Prestige extends JavaPlugin {
    private MySQL sql;
    private SQLite sqLite;
    private MessagesConfig messagesConfig;
    private PrestigeConfig prestigeConfig;
    private BonusManager bonusManager;
    private PrestigeManager prestigeManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        setupSql();
        setupManagers();
        if(this.isEnabled()) {
            registerEvents();
            registerCommands();
            loadPlayers();
        }
    }

    @Override
    public void onDisable() {
        if(this.prestigeManager != null) {
            savePlayers();
        }
        sql.disconnect();
    }
    private void setupSql(){
        this.sqLite = new SQLite(this);
        this.sql = new MySQL(this.getConfig(), sqLite);
        sql.connect();
        sql.createTable();
    }
    private void setupManagers(){
        this.messagesConfig = new MessagesConfig(this);
        this.prestigeConfig = new PrestigeConfig(this);
        this.prestigeManager = new PrestigeManager(sql, messagesConfig.getConfiguration());
        this.bonusManager = new BonusManager(prestigeConfig, prestigeManager);
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new PrestigePlaceHolderExpansion(this, prestigeManager).register();
        }
    }

    public void registerCommands(){
        getCommand("prestige").setExecutor(new PrestigeCommand(prestigeManager, messagesConfig, bonusManager));
    }
    public void registerEvents(){
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new InventoryClick(prestigeManager, messagesConfig, prestigeConfig), this);
        pm.registerEvents(new PlayerQuit(prestigeManager), this);
        pm.registerEvents(new PlayerJoin(prestigeManager), this);
    }

    public void loadPlayers(){
        for(Player p : Bukkit.getOnlinePlayers()){
            prestigeManager.loadPrestige(p);
        }
    }

    public void savePlayers(){
        for(Player p : Bukkit.getOnlinePlayers()){
            prestigeManager.savePrestige(p);
        }
    }

    public PrestigeConfig getPrestigeConfig() {
        return prestigeConfig;
    }

    public PrestigeManager getPrestigeManager() {
        return prestigeManager;
    }
}
