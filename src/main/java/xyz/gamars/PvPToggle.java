package xyz.gamars;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.gamars.commands.PvPToggleCommand;
import xyz.gamars.listeners.AttackPlayerListener;
import xyz.gamars.listeners.PlayerJoinListener;

public final class PvPToggle extends JavaPlugin {

    public void registerEvent(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, this);
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();

        getCommand("pvptoggle").setExecutor(new PvPToggleCommand());
        registerEvent(new AttackPlayerListener());
        registerEvent(new PlayerJoinListener());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

    }
}
