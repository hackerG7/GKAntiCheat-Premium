
package me.club.gkanticheat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


public class Main extends JavaPlugin implements Listener {
    public static int seconds = 20;
    public static Main main;
    public JavaPlugin localPlugin;
    public static Plugin plugin;
    public static HashMap<UUID, PlayerData> lockedList = new HashMap<>();

    public static FileConfiguration config;
    public static FileConfiguration players;


    public static String color(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }

    public static String dependency = "essentials";
    public static String topString = color("&e--------------------------");
    public static String bottomString = color("&e--------------------------");

    public static String systemName = ChatColor.translateAlternateColorCodes('&', "&8銆�&a闃插鎺涚郴绲�&8銆�");
    public static String successString;
    public static String failString;
    public static String kickString;
    public static String banString;
    public static boolean useBan = true;
    public static int banAttempt = 3;
    public static String whiteString;
    public static String failsString;
    public static String hacksString;
    public static String addWhiteString;
    public static String removeWhiteString;
    public static String cannotFindPlayerString;
    public static String remainingTimeString;
    public static String secondString;
    public static String buttonString;
    public static String reloadString;
    public static String warningTitleString;
    public static String warningSubtitleString;
    public static String successTitleString;
    public static String successSubtitleString;
    public static List<String> helpList = new ArrayList<>();


    public static void run_server_command(String cmd) {
        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), cmd);

    }

    static FileConfiguration getPlayers() {
        File logs = new File(plugin.getDataFolder(), "players.yml");
        if (!logs.exists()) {
            try {
                logs.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.print("Cannot create logs.yml");
            }
        }
        YamlConfiguration logsz = YamlConfiguration.loadConfiguration(logs);
        logsz.options().copyDefaults(true);
        return logsz;
    }

    static void savePlayers() {
        File file = new File(plugin.getDataFolder(), "players.yml");
        try {
            players.save(file);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @SuppressWarnings("deprecation")
    public static void checkPlayer(Player p) {
        UUID uid = p.getUniqueId();
        if (!Main.lockedList.containsKey(uid)) {
            Main.lockedList.put(uid, new PlayerData(uid, p.getName()));
        }
        //premium
        p.sendTitle(Main.warningTitleString, Main.warningSubtitleString);
        new BukkitRunnable() {
            Player localPlayer = p;
            int secondLeft = Main.seconds;

            @Override
            public void run() {
                secondLeft--;
                if (Main.lockedList.containsKey(localPlayer.getUniqueId())) {
                    if (localPlayer.isOnline()) {

                        //Main.run_server_command("tellraw "+localPlayer.getName()+" [{\"text\":\"銆愰槻澶栨帥绯荤当銆慭",\"bold\":true,\"color\":\"green\"},{\"text\":\"  榛炴垜閫氶亷娓│     \",\"bold\":true,\"color\":\"aqua\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"."+".say "+Main.lockedList.get(localPlayer.getUniqueId())+"\"}}]");

                        localPlayer.sendMessage(Main.systemName + Main.topString);
                        Main.run_server_command("tellraw " + localPlayer.getName() + " [\"\",{\"text\":\"" + Main.systemName + "\",\"color\":\"green\",\"bold\":true},{\"text\":\"████████████\",\"color\":\"aqua\",\"bold\":true,\"clickEvent\":{\"action\":\"run_command\",\"value\":\"" + ".say " + Main.lockedList.get(localPlayer.getUniqueId()).key + "\"}}]");
                        Main.run_server_command("tellraw " + localPlayer.getName() + " [\"\",{\"text\":\"" + Main.systemName + "\",\"color\":\"green\",\"bold\":true},{\"text\":\"████████████\",\"color\":\"aqua\",\"bold\":true,\"clickEvent\":{\"action\":\"run_command\",\"value\":\"" + ".say " + Main.lockedList.get(localPlayer.getUniqueId()).key + "\"}}]");
                        Main.run_server_command("tellraw " + localPlayer.getName() + " [\"\",{\"text\":\"" + Main.systemName + "\",\"color\":\"green\",\"bold\":true},{\"text\":\"████████████\",\"color\":\"aqua\",\"bold\":true,\"clickEvent\":{\"action\":\"run_command\",\"value\":\"" + ".say " + Main.lockedList.get(localPlayer.getUniqueId()).key + "\"}}]");
                        Main.run_server_command("tellraw " + localPlayer.getName() + " [\"\",{\"text\":\"" + Main.systemName + "\",\"color\":\"green\",\"bold\":true},{\"text\":\"████████████\",\"color\":\"aqua\",\"bold\":true,\"clickEvent\":{\"action\":\"run_command\",\"value\":\"" + ".say " + Main.lockedList.get(localPlayer.getUniqueId()).key + "\"}},{\"text\":\" " + Main.remainingTimeString + ": \",\"color\":\"red\",\"bold\":false},{\"text\":\"" + secondLeft + "" + Main.secondString + "\",\"color\":\"yellow\",\"bold\":true}]");
                        Main.run_server_command("tellraw " + localPlayer.getName() + " [\"\",{\"text\":\"" + Main.systemName + "\",\"color\":\"green\",\"bold\":true},{\"text\":\"████████████\",\"color\":\"aqua\",\"bold\":true,\"clickEvent\":{\"action\":\"run_command\",\"value\":\"" + ".say " + Main.lockedList.get(localPlayer.getUniqueId()).key + "\"}}]");
                        Main.run_server_command("tellraw " + localPlayer.getName() + " [\"\",{\"text\":\"" + Main.systemName + "\",\"color\":\"green\",\"bold\":true},{\"text\":\"████████████\",\"color\":\"aqua\",\"bold\":true,\"clickEvent\":{\"action\":\"run_command\",\"value\":\"" + ".say " + Main.lockedList.get(localPlayer.getUniqueId()).key + "\"}}]");
                        Main.run_server_command("tellraw " + localPlayer.getName() + " [\"\",{\"text\":\"" + Main.systemName + "\",\"color\":\"green\",\"bold\":true},{\"text\":\"████████████\",\"color\":\"aqua\",\"bold\":true,\"clickEvent\":{\"action\":\"run_command\",\"value\":\"" + ".say " + Main.lockedList.get(localPlayer.getUniqueId()).key + "\"}}]");
                        localPlayer.sendMessage(Main.systemName + Main.bottomString);


                    } else {
                        this.cancel();

                    }
                } else {
                    this.cancel();
                    
                }

            }

        }.runTaskTimer(plugin, 0, 20);

        new BukkitRunnable() {
            Player localPlayer = p;

            @Override
            public void run() {
                if (Main.lockedList.containsKey(localPlayer.getUniqueId())) {
                    if (localPlayer.isOnline()) {
                        localPlayer.sendMessage(Main.systemName + Main.failString);
                        localPlayer.kickPlayer(Main.failString);
                        Main.addFails(localPlayer.getUniqueId(), 1);
                    } else {
                        this.cancel();
                    }
                }

            }

        }.runTaskLater(plugin, 20 * Main.seconds);
    }

    public static void updatePlayer(UUID uid) {

        String suid = uid.toString();
        if (!Main.players.contains(suid)) {
            ConfigurationSection section = Main.players.createSection(suid);
            OfflinePlayer player = Bukkit.getOfflinePlayer(uid);
            section.set("name", player.getName());
            section.set("white", false);
            section.set("fails", 0);
            section.set("hacks", 0);
        }
    }

    public static void setWhite(UUID uid, boolean bool) {
        String suid = uid.toString();
        updatePlayer(uid);
        Main.players.set(suid + ".white", bool);
    }

    public static void addFails(UUID uid, int amount) {
        String suid = uid.toString();
        updatePlayer(uid);
        int current = Main.players.getInt(suid + ".fails");
        Main.players.set(suid + ".fails", current + amount);
    }

    public static void addHacks(UUID uid, int amount) {
        String suid = uid.toString();
        updatePlayer(uid);
        int current = Main.players.getInt(suid + ".hacks");
        Main.players.set(suid + ".hacks", current + amount);
    }

    public static void setFails(UUID uid, int fails) {
        String suid = uid.toString();
        updatePlayer(uid);
        Main.players.set(suid + ".fails", fails);
    }

    public static void setHacks(UUID uid, int hacks) {
        String suid = uid.toString();
        updatePlayer(uid);
        Main.players.set(suid + ".hacks", hacks);
    }

    public static void success(Player player) {
        Main.lockedList.remove(player.getUniqueId());
        player.sendMessage(Main.systemName + Main.successString);
        player.sendTitle(Main.successTitleString, Main.successSubtitleString);
    }

    public static void fail(Player player) {
        kickHacker(player);
        Main.lockedList.remove(player.getUniqueId());
    }

    public static void kickHacker(Player player) {
        addHacks(player.getUniqueId(), 1);
        int hacks = Main.players.getInt(player.getUniqueId().toString() + ".hacks");

        if (hacks < Main.banAttempt) {
            String str = Main.kickString;
            str = str.replace("%amount%", "" + hacks);
            player.kickPlayer(str);
            //Main.run_server_command("cmi broadcast &a鐜╁&e"+player.getName()+"&c琚娓埌绗�&b&l"+hacks+"&c娆′娇鐢�&4澶栨帥鎴栭潪姝ｇ増鐨凪inecraft&c锛岀偤姝ょ郸浜堣鍛婏紝鍐嶇姱鍓�&4&lBAN");

        } else {
            if (Main.useBan) {
                int days = hacks - 2;
                int seconds = days * 86400;
                String str = Main.banString;
                str = str.replace("%amount%", "" + hacks);
                str = str.replace("%days%", "" + days);
                //Main.run_server_command("cmi broadcast &a鐜╁&e"+player.getName()+"&c琚娓埌绗�&4&l"+hacks+"&c娆′娇鐢�&4澶栨帥鎴栭潪姝ｇ増鐨凪inecraft&c鎳茬桨: &4&lBAN "+days+"澶�");
                switch (Main.dependency.toLowerCase()) {
                    default:
                    case "essential":
                    case "essentials":
                    case "ess":
                        Main.run_server_command("tempban " + player.getName() + " " + days + "d");
                        break;
                    case "cmi":
                        Main.run_server_command("cmi tempban " + player.getName() + " " + seconds + " " + str);
                        break;
                }
            }
        }
    }

    public static void loadConfig() {
        plugin.reloadConfig();
        config = plugin.getConfig();
        Main.dependency = color(config.getString("dependency"));
        Main.topString = color(config.getString("topString"));
        Main.bottomString = color(config.getString("bottomString"));
        systemName = color(config.getString("systemName"));
        Main.successString = color(config.getString("successString"));
        Main.failString = color(config.getString("failString"));
        Main.kickString = color(config.getString("kickString"));
        Main.banString = color(config.getString("banString"));
        Main.useBan = config.getBoolean("useBan");
        Main.banAttempt = config.getInt("banAttempt");
        Main.whiteString = color(config.getString("whiteString"));
        Main.failsString = color(config.getString("failsString"));
        Main.hacksString = color(config.getString("hacksString"));
        Main.addWhiteString = color(config.getString("addWhiteString"));
        Main.removeWhiteString = color(config.getString("removeWhiteString"));
        Main.cannotFindPlayerString = color(config.getString("cannotFindPlayerString"));
        Main.remainingTimeString = color(config.getString("remainingTimeString"));
        Main.secondString = color(config.getString("secondString"));
        Main.buttonString = color(config.getString("buttonString"));
        Main.reloadString = color(config.getString("reloadString"));
        Main.helpList = config.getStringList("helpList");
        Main.warningTitleString = color(config.getString("warningTitleString"));
        Main.warningSubtitleString = color(config.getString("warningSubtitleString"));
        Main.successTitleString = color(config.getString("successTitleString"));
        Main.successSubtitleString = color(config.getString("successSubtitleString"));
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onEnable() {
        localPlugin = this;
        plugin = localPlugin;
        config = localPlugin.getConfig();
    	OfflinePlayer G7 = Bukkit.getOfflinePlayer(UUID.fromString("5162d18a-def7-4238-b761-0a84654abafd"));
    	
    	if(G7.getName()!=null) {
	    	if(G7.getName().equals("hiIamG7")) {
	    		initiate_server();
	    		Bukkit.broadcastMessage(color("&aGKAntiCheat Premium version is testing on this server."));
	    		
	    	}else {
	    		Bukkit.broadcastMessage(color("&4GKAntiCheat Premium testing version was disabled by author. Please contact author: G7 for a new version."));

	    	}
    	}else {
    		Bukkit.broadcastMessage(color("&4GKAntiCheat failed, please turn on online-mode in server.properties"));
    	}
    }

    public void initiate_server() {
        loadConfig();
        this.saveDefaultConfig();
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
        players = getPlayers();
        localPlugin.getCommand("gkac").setExecutor(new CommandClass());
        getServer().getPluginManager().registerEvents(new GKEvent(this.getPlugin(Main.class)), this);
        new BukkitRunnable() {
            @Override
            public void run() {
                Main.savePlayers();
            }

        }.runTaskTimerAsynchronously(Main.getPlugin(Main.class), 200, 200);

    }

    @Override
    public void onDisable() {
        savePlayers();
        System.out.print("fuck off");
    }
}


