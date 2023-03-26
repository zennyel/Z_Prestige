package com.zennyel.orion.database;

import com.zennyel.orion.Z_Prestige;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQL {
    private String host;
    private int port;
    private String database;
    private String username;
    private String password;
    private String table;

    private Connection connection;

    public MySQL(FileConfiguration config) {
        this.host = config.getString("MySQL.host");
        this.port = config.getInt("MySQL.port");
        this.database = config.getString("MySQL.database");
        this.username = config.getString("MySQL.username");
        this.password = config.getString("MySQL.password");
        this.table = config.getString("MySQL.table");
    }

    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?useSSL=false", username, password);
            Bukkit.getConsoleSender().sendMessage("§a§lConnected to database!");
        } catch (ClassNotFoundException | SQLException e) {
            Bukkit.getConsoleSender().sendMessage("§c§lFailed to database connection, plugin shutingdown!");
            Bukkit.getConsoleSender().sendMessage("§c§lError:" + e.getCause());
            Bukkit.getPluginManager().disablePlugin(Z_Prestige.getPlugin(Z_Prestige.class));
        }
    }

    public void disconnect() {
        try {
            if (connection != null) {
                connection.close();
                Bukkit.getConsoleSender().sendMessage("§a§lDisconnecting of database!");
            }
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage("§a§lDisconnecting of database!");
        }
    }

    public void createTable() {
        try {
            PreparedStatement ps = connection.prepareStatement("CREATE TABLE IF NOT EXISTS " + table + " (uuid VARCHAR(36) NOT NULL PRIMARY KEY, prestige INT NOT NULL)");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getPrestige(String uuid) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT prestige FROM " + table + " WHERE uuid = ?");
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("prestige");
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void setPrestige(String uuid, int prestige) {
        try {
            PreparedStatement ps = connection.prepareStatement("REPLACE INTO " + table + " (uuid, prestige) VALUES (?, ?)");
            ps.setString(1, uuid);
            ps.setInt(2, prestige);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
