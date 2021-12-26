// 
// Decompiled by Procyon v0.5.36
// 

package net.ender.cc.scoring.commands;

import net.ender.cc.scoring.Scoring;
import net.ender.cc.scoring.data.ScoringTeam;
import net.ender.cc.scoring.sql.SQLPlayer;
import net.ender.cc.scoring.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

//TODO: looks like decompiled code or awful naming scheme pls fix later
public class PlayerTeamCMD implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (args.length == 0) {
            if (sender instanceof Player) {
                SQLPlayer getter = new SQLPlayer((Player) sender);
                ScoringTeam teamName = getter.getTeam();
                sender.sendMessage(Util.colorize("&aYou are on team &6" + teamName));
            }
        } else if (args.length == 2) {
            if (sender.hasPermission("scoring.admin")) {
                if (Scoring.getInstance().getConfig().getBoolean("mysql.use-mysql")) {
                    String lowerCase3 = args[0].toLowerCase();
                    if (lowerCase3.equals("reset")) {
                        if (args[1] == null) {
                            sender.sendMessage(Util.colorize("&cMissing arguments!"));
                            return false;
                        }

                        Player target = Bukkit.getPlayer(args[1]);
                        SQLPlayer targetDataHandler = new SQLPlayer(target);
                        sender.sendMessage(Util.colorize("&cRemoved &6" + target.getName() + "&6's &cTeam"));
                        targetDataHandler.setTeamName("NONE");
                    }
                }
            } else {
                sender.sendMessage(Util.colorize("&cYou must be admin or higher to use this command."));
            }

            return false;
        } else if (args.length == 3) {
            if (sender.hasPermission("scoring.admin")) {
                if (Scoring.getInstance().getConfig().getBoolean("mysql.use-mysql")) {
                    String lowerCase = args[0].toLowerCase();
                    if (lowerCase.equals("set")) {
                        if (args[1] == null) {
                            sender.sendMessage(Util.colorize("&cMissing arguments!"));
                            return false;
                        }

                        Player target = Bukkit.getPlayer(args[1]);
                        SQLPlayer targetDataHandler = new SQLPlayer(target);

                        if (ScoringTeam.anyMatch(args[2])) {
                            ScoringTeam team = ScoringTeam.valueOf(args[2].toUpperCase());

                            sender.sendMessage(Util.colorize(ChatColor.GREEN + target.getName() + "&a's team got set to &6" + team.getFormattedName()));
                            targetDataHandler.setTeamName(team.name());
                        }

                        return false;
                    }
                }
            } else {
                sender.sendMessage(Util.colorize("&cYou must be admin or higher to use this command."));
            }
        }

        return false;
    }
}
