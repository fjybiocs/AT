package com.mcfuntime.at;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Sound.ENTITY_PLAYER_LEVELUP;

public class MessageListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onPostMessage(AsyncPlayerChatEvent event) {
        // 分割字符串，找出可能存在艾特玩家的片段
        String content = event.getMessage();
        String [] arr = content.split("\\s+");

        Player player = event.getPlayer();
        StringBuilder output = new StringBuilder();
        List<Player> desPlayerList = new ArrayList<Player>();
        // 查找每个片段中是否存在艾特
        for(String ss : arr){
            if(ss.charAt(0) == '@' && ss.length() > 1){
                String username = ss.substring(1);
                Player desPlayer = Bukkit.getPlayer(username);
                if(desPlayer == null){
                    player.sendMessage("§c[交大助手] " + username + "不在线");
                }else{
                    desPlayerList.add(desPlayer);
                    output.append("§b@").append(desPlayer.getName()).append("§f").append(" ");
                }
            }else{
                output.append(ss);
            }
        }
        // 给每个被艾特的玩家播放声音
        for(Player p : desPlayerList){
            p.playSound(player, ENTITY_PLAYER_LEVELUP,1, 1);
        }
        event.setMessage(output.toString());
    }
}
