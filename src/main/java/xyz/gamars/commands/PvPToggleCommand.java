package xyz.gamars.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import xyz.gamars.PvPToggle;

public class PvPToggleCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = ((Player) sender).getPlayer();
        FileConfiguration config = PvPToggle.getPlugin(PvPToggle.class).getConfig();

        config.set("players." + player.getDisplayName() + ".enabled", !(config.getBoolean("players." + player.getDisplayName() + ".enabled")));
        PvPToggle.getPlugin(PvPToggle.class).saveConfig();
        if ((config.getBoolean("players." + player.getDisplayName() + ".enabled"))) {
            sender.sendMessage("PVP has been disabled");
        } else {
            sender.sendMessage("PVP has been enabled");
        }

        return true;


    }
}
