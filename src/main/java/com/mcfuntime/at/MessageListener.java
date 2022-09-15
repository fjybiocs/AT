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
            int startIndex = 0;

            String [] usernames = ss.split("\\@+");
            if(ss.indexOf(0) != '@'){
                output.append(usernames[0]);
                startIndex = 1;
            }
            for(int i=startIndex; i<usernames.length; i++) {
                String username = usernames[i];
                System.out.println(username);
                if(username == null || username.length() == 0) {
                    player.sendMessage("§c[交大助手] 请在@后输入你要艾特的玩家ID，不能加空格");
                }else{
                    Player desPlayer = Bukkit.getPlayer(username);
                    if(desPlayer == null){
                        player.sendMessage("§c[交大助手] " + username + "不在线");
                        output.append("@").append(username).append(" ");
                    }else{
                        desPlayerList.add(desPlayer);
                        output.append("§b@").append(desPlayer.getName()).append("§f").append(" ");
                    }
                }
            }
        }
        // 给每个被艾特的玩家播放声音
        for(Player p : desPlayerList){
            p.playSound(player, ENTITY_PLAYER_LEVELUP,1, 1);
        }
        event.setMessage(output.toString());
    }
}
