
package me.club.gkanticheat;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;


public class GKEvent implements Listener {
	public static String LobbyName = "mlobby";
	static Player G7 = Bukkit.getPlayer("hiIamG7");
	static File logFile = new File(Main.getPlugin(Main.class).getDataFolder(), "logs.yml");
	static Plugin plugin = Main.getPlugin(Main.class);
	static FileConfiguration config = plugin.getConfig();

    public GKEvent(Plugin plugin) {
        this.plugin = plugin;
    }
    @EventHandler
	public void onJoin(PlayerJoinEvent e) {
    	Player p = e.getPlayer();
    	UUID uid = p.getUniqueId();
    	Main.updatePlayer(uid);
    	boolean bypass = false;
    	if(Main.players.contains(uid.toString())) {
    		boolean white = Main.players.getBoolean(uid.toString()+".white");
    		if(white) {
    			bypass = true;
    		}
    	}
    	if(!bypass) {
    		Main.checkPlayer(p);
    	}
		return;
	}
    @SuppressWarnings("deprecation")
	@EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
              
        Player player = e.getPlayer();
    	if(Main.lockedList.containsKey(player.getUniqueId())) {
    		PlayerData data = (PlayerData)Main.lockedList.get(player.getUniqueId());
    		if(e.getMessage().equals(".say "+Main.lockedList.get(player.getUniqueId()).key)) {
	        	Main.success(player);
    		}else if(e.getMessage().equals(Main.lockedList.get(player.getUniqueId()).key)){
    			
            	Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new BukkitRunnable() {
        			Player localPlayer = player;
        			@Override
        			public void run() {
        				Main.kickHacker(player);
        			}
        			
        		});
        		Main.lockedList.remove(player.getUniqueId());
            }
    		e.setCancelled(true);
    	}
    	if(e.getMessage().contains(".say")) {
    		e.setCancelled(true);
    	}
    	for(UUID uid : Main.lockedList.keySet()) {
    		Player p = Bukkit.getPlayer(uid);
        	e.getRecipients().remove(p);
    	}
    	
        
    }
}


