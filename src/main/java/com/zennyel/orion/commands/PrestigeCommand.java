package com.zennyel.orion.commands;

import com.zennyel.orion.Z_Prestige;
import com.zennyel.orion.menu.PrestigeGUI;
import com.zennyel.orion.other.MessagesConfig;
import com.zennyel.orion.prestige.BonusManager;
import com.zennyel.orion.prestige.Prestige;
import com.zennyel.orion.prestige.PrestigeManager;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.meta.FireworkMeta;

public class PrestigeCommand implements CommandExecutor {
    private PrestigeManager manager;
    private MessagesConfig messagesConfig;
    private BonusManager bonusManager;

    public PrestigeCommand(PrestigeManager manager, MessagesConfig messagesConfig, BonusManager bonusManager) {
        this.manager = manager;
        this.messagesConfig = messagesConfig;
        this.bonusManager = bonusManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            return false;
        }
        Player player = (Player) sender;

        if(args.length == 0){
            Inventory prestige = Bukkit.createInventory(null, 27, "§8Confirm prestiging!");
            new PrestigeGUI(prestige, player);
            return false;
        }

        switch (args[0]){
            case "confirm":
                if(manager.isPrestiging(player)){
                    int prestige = manager.getPrestige(player).getPrestige();
                    player.sendMessage(messagesConfig.getMessage("Messages.confirm", player));
                    spawnFireworks(player);
                    manager.setIsPrestiging(player, false);
                    manager.setPrestige(player, new Prestige(prestige + 1));
                    bonusManager.executeCommand(player, prestige);
                }else{
                    player.sendMessage(messagesConfig.getMessage("Messages.error_not_prestiging", player));
                }
                break;
            default:
                player.sendMessage(messagesConfig.getMessage("Messages.command_not_found", player));
        }

        return false;
    }

    public void spawnFireworks(Player player){
        Location loc = player.getLocation();
        Bukkit.getScheduler().scheduleSyncDelayedTask(Z_Prestige.getPlugin(Z_Prestige.class), new Runnable() {
            @Override
            public void run() {
                final Firework f = player.getWorld().spawn(loc, Firework.class);
                FireworkMeta fm = f.getFireworkMeta();

                fm.addEffect(FireworkEffect.builder()

                        .flicker(true)
                        .trail(true)
                        .with(FireworkEffect.Type.STAR)
                        .with(FireworkEffect.Type.BALL)
                        .with(FireworkEffect.Type.BALL_LARGE)
                        .withColor(Color.AQUA)
                        .withColor(Color.YELLOW)
                        .withColor(Color.RED)
                        .withColor(Color.WHITE)
                        .build());

                fm.setPower(0);
                f.setFireworkMeta(fm);
            }
        }, 5L);
    }

}
