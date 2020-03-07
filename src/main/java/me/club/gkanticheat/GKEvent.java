
package me.club.gkanticheat;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.UUID;


public class GKEvent implements Listener {
    public static String LobbyName = "mlobby";
    private static Player G7 = Bukkit.getPlayer("hiIamG7");
    private static File logFile = new File(Main.getPlugin(Main.class).getDataFolder(), "logs.yml");
    private static Plugin plugin = Main.getPlugin(Main.class);
    private static FileConfiguration config = plugin.getConfig();

    public GKEvent(Plugin plugin) {
        GKEvent.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        UUID uid = p.getUniqueId();
        Main.updatePlayer(uid);
        boolean bypass = false;
        if (Main.players.contains(uid.toString())) {
            boolean white = Main.players.getBoolean(uid.toString() + ".white");
            if (white) {
                bypass = true;
            }
        }
        if (!bypass) {
            Main.checkPlayer(p);
        }
    }

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        if (Main.lockedList.containsKey(player.getUniqueId())) {
            if (e.getMessage().equals(".say " + Main.lockedList.get(player.getUniqueId()).key)) {
                Main.success(player);
            } else if (e.getMessage().equals(Main.lockedList.get(player.getUniqueId()).key)) {
                Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new BukkitRunnable() {
                    @Override
                    public void run() {
                        Main.kickHacker(player);
                    }
                });
                Main.lockedList.remove(player.getUniqueId());
            }
            e.setCancelled(true);
        }
        if (e.getMessage().contains(".say")) {
            e.setCancelled(true);
        }else {
        	
        }
        for (UUID uid : Main.lockedList.keySet()) {
            Player p = Bukkit.getPlayer(uid);
            e.getRecipients().remove(p);
        }
    }
}


