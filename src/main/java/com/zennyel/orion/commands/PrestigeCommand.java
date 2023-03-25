package com.zennyel.orion.commands;

import com.zennyel.orion.menu.PrestigeGUI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class PrestigeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            return false;
        }
        Player player = (Player) sender;

        if(args.length == 0){
            Inventory prestige = Bukkit.createInventory(null, 27);
            new PrestigeGUI(prestige);
            player.openInventory(prestige);
        }

        switch (args[1]){
            case "confirm":
                break;
            case "cancel":
                break;
        }

        return false;
    }
}