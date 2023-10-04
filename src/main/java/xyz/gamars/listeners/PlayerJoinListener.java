package xyz.gamars.listeners;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import xyz.gamars.PvPToggle;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        FileConfiguration config = PvPToggle.getPlugin(PvPToggle.class).getConfig();
        Player player = event.getPlayer();
        if (!config.contains("players." + player.getDisplayName())) {
            config.set("players." + player.getDisplayName() + ".enabled", false);
            PvPToggle.getPlugin(PvPToggle.class).saveConfig();
        }
    }

}
