
package me.club.gkanticheat;



import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import me.club.gkanticheat.Main;

import java.util.UUID;


public class CommandClass implements CommandExecutor {
    static Plugin plugin = Main.getPlugin(Main.class);
    static FileConfiguration config = plugin.getConfig();
    
    public void help(CommandSender sender) {
    }

    @SuppressWarnings({"deprecation"})
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        final Player player = (Player) sender;
        UUID playerUid = null;
        if (sender instanceof Player) {
            playerUid = player.getUniqueId();
        }
        boolean success = true;
        if (args.length > 0) {
            args[0] = args[0].toLowerCase();
            switch (args[0]) {
                case "help":
                    if (sender.hasPermission("gkac.help")) {
                        for (String str : Main.helpList) {
                            player.sendMessage(Main.color("&a" + str));
                        }
                    }
                    break;
                case "reload":
                case "reloadconfig":
                case "reload_config":
                    if (sender.hasPermission("gkac.reload")) {
                        Main.loadConfig();
                        player.sendMessage(Main.systemName + Main.reloadString);
                    }
                    break;
                case "list":
                case "listplayer":
                case "list_player":
                    if (sender.hasPermission("gkac.list")) {
                        player.sendMessage(Main.systemName + Main.whiteString + ": ");
                        for (String suid : Main.players.getKeys(false)) {
                            if (Main.players.getBoolean(suid + ".white")) {
                                UUID uid = UUID.fromString(suid);
                                OfflinePlayer p = Bukkit.getOfflinePlayer(uid);
                                player.sendMessage(Main.systemName + ChatColor.YELLOW + "  " + p.getName());
                            }
                        }
                    }
                    break;
                case "add":
                case "addplayer":
                case "add_player":
                    if (sender.hasPermission("gkac.add")) {
                        if (args.length > 1) {
                            String playerName = args[1];
                            OfflinePlayer p = Bukkit.getOfflinePlayer(playerName);
                            if (p != null) {
                                Main.setWhite(p.getUniqueId(), true);
                                player.sendMessage(Main.systemName + Main.addWhiteString + ": " + playerName);
                            } else {
                                player.sendMessage(Main.systemName + Main.cannotFindPlayerString + ": " + playerName);

                            }
                        }
                    }
                    break;
                case "remove":
                case "removeplayer":
                case "remove_player":
                    if (sender.hasPermission("gkac.remove")) {
                        if (args.length > 1) {
                            String playerName = args[1];
                            OfflinePlayer p = Bukkit.getOfflinePlayer(playerName);
                            if (p != null) {
                                Main.setWhite(p.getUniqueId(), false);
                                player.sendMessage(Main.systemName + Main.addWhiteString + ": " + playerName);
                            } else {
                                player.sendMessage(Main.systemName + Main.cannotFindPlayerString + ": " + playerName);

                            }


                        }
                    }
                    break;
                case "check":
                case "checkplayer":
                case "check_player":
                    if (sender.hasPermission("gkac.check")) {
                        if (args.length > 1) {
                            String playerName = args[1];
                            OfflinePlayer p = Bukkit.getOfflinePlayer(playerName);
                            UUID uid = p.getUniqueId();
                            String suid = uid.toString();
                            Main.updatePlayer(uid);
                            boolean white = Main.players.getBoolean(suid + ".white");
                            int fails = Main.players.getInt(suid + ".fails");
                            int hacks = Main.players.getInt(suid + ".hacks");
                            player.sendMessage(Main.systemName + ChatColor.AQUA + playerName + ChatColor.GREEN + ": ");
                            player.sendMessage(Main.systemName + ChatColor.WHITE + "   " + Main.whiteString + ": " + ChatColor.YELLOW + white);
                            player.sendMessage(Main.systemName + "   " + Main.failsString + ": " + ChatColor.YELLOW + fails);
                            player.sendMessage(Main.systemName + "   " + Main.hacksString + ": " + ChatColor.YELLOW + hacks);

                        }

                    }
                    break;
            }
        }
        return false;
    }

}

