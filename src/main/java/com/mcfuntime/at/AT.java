package com.mcfuntime.at;

import org.bukkit.plugin.java.JavaPlugin;

public final class AT extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getCommand("at").setExecutor(new ATCommandExecutor());
        getServer().getPluginManager().registerEvents(new MessageListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
