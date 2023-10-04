package xyz.gamars.listeners;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.*;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustByEntityEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import xyz.gamars.PvPToggle;

public class AttackPlayerListener implements Listener {

    public static void cancelDamage(EntityDamageByEntityEvent event) {
        Player attackingPlayer = ((Player) event.getDamager()).getPlayer();
        Player attackedPlayer = ((Player) event.getEntity()).getPlayer();
        FileConfiguration config = PvPToggle.getPlugin(PvPToggle.class).getConfig();
        if (config.getBoolean("players." + attackedPlayer.getDisplayName() + ".enabled") ||
                config.getBoolean("players." + attackingPlayer.getDisplayName() + ".enabled")) {
            event.setCancelled(true);
            return;
        }
    }

    public static void cancelDamage(EntityCombustByEntityEvent event) {
        Player attackingPlayer = ((Player) event.getCombuster()).getPlayer();
        Player attackedPlayer = ((Player) event.getEntity()).getPlayer();
        FileConfiguration config = PvPToggle.getPlugin(PvPToggle.class).getConfig();
        if (config.getBoolean("players." + attackedPlayer.getDisplayName() + ".enabled") ||
                config.getBoolean("players." + attackingPlayer.getDisplayName() + ".enabled")) {
            event.setCancelled(true);
            return;
        }
    }

    @EventHandler
    public void onAttackPlayer(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
            cancelDamage(event);
        }
        if (event.getDamager() instanceof Projectile && event.getEntity() instanceof Player) {
            Projectile projectile = (Projectile) event.getDamager();
            if (projectile.getShooter() instanceof Player) {
                cancelDamage(event);
            }
        }


    }

    @EventHandler
    public void onCombustPlayer(EntityCombustByEntityEvent event) {
        if (event.getCombuster() instanceof Player && event.getEntity() instanceof Player) {
            cancelDamage(event);
        }

        if (event.getCombuster() instanceof Projectile) {
            Projectile projectile = (Projectile) event.getCombuster();
            if (projectile.getShooter() instanceof Player) {
                cancelDamage(event);
            }
        }
    }

}
