package com.zennyel.orion.database;

import com.zennyel.orion.Z_Prestige;
import org.bukkit.Bukkit;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLite {
    private Connection connection;
    private Z_Prestige instance;

    public SQLite(Z_Prestige instance) {
        this.instance = instance;
    }

    public void connect(){
        File dataFolder = new File(instance.getDataFolder(), "database.db");
        if(!dataFolder.exists()){
            try {
                dataFolder.createNewFile();
            }catch (IOException e){
                Bukkit.getConsoleSender().sendMessage("§cError creating database file: " + e.getCause());
            }
        }
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + dataFolder);
            Bukkit.getConsoleSender().sendMessage("§aSuccessfully connected to sqlite!");
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
            Bukkit.getConsoleSender().sendMessage("§cError connecting to database: ClassNotFound/SQLException");
            Bukkit.getPluginManager().disablePlugin(Z_Prestige.getPlugin(Z_Prestige.class));
        }

    }

    public Connection getConnection() {
        return connection;
    }
}
