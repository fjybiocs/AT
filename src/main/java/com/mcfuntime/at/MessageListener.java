package com.mcfuntime.at;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.bukkit.Sound.ENTITY_PLAYER_LEVELUP;
import static org.bukkit.event.EventPriority.HIGHEST;

public class MessageListener implements Listener {

    @EventHandler(ignoreCancelled = true, priority = HIGHEST)
    public void onPostMessage(AsyncPlayerChatEvent event) {
        String content = event.getMessage();

        if(content.startsWith("@all") && event.getPlayer().isOp()){
            event.setMessage("§b@all§f" + content.substring(4));
            Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();
            for(Player p : onlinePlayers){
                p.playSound(p, ENTITY_PLAYER_LEVELUP,1, 1);
            }
            return;
        }

        // split by blank space
        String [] arr = content.split("\\s+");

        Player player = event.getPlayer();
        StringBuilder output = new StringBuilder();
        List<Player> desPlayerList = new ArrayList<Player>();
        // find @
        for(String ss : arr){
            int startIndex = 0;

            String [] usernames = ss.split("\\@+");
            if(ss.indexOf(0) != '@'){
                output.append(usernames[0]).append(" ");
                startIndex = 1;
            }
            for(int i=startIndex; i<usernames.length; i++) {
                String username = usernames[i];
                System.out.println(username);
                if(username == null || username.length() == 0) {
                    player.sendMessage("§c[AT] unknown user");
                }else{
                    Player desPlayer = Bukkit.getPlayer(username);
                    if(desPlayer == null){
                        player.sendMessage("§c[AT] " + username + "is offline.");
                        output.append("@").append(username).append(" ");
                    }else{
                        desPlayerList.add(desPlayer);
                        output.append("§b@").append(desPlayer.getName()).append("§f").append(" ");
                    }
                }
            }
        }
        // play sound for each player
        for(Player p : desPlayerList){
            p.playSound(p, ENTITY_PLAYER_LEVELUP,1, 1);
        }
        event.setMessage(output.toString());
    }
}
