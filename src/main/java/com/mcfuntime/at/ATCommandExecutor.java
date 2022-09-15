package com.mcfuntime.at;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Sound.ENTITY_PLAYER_LEVELUP;

public class ATCommandExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = ((Player) sender).getPlayer();
            // 对指令进行预检查
            if(args.length == 0 || args[0] == null || args[0].length() == 0){
                player.sendMessage("§c[AT] unknown user.");
                return true;
            }
            // 查找玩家
            Player desPlayer = Bukkit.getPlayer(args[0]);
            if(desPlayer == null){
                player.sendMessage("§c[交大助手] the user is offline.");
                return true;
            }
            // 执行提醒
            player.chat( "@" + args[0]);
        }
        return true;
    }
}
