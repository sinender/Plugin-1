// 
// Decompiled by Procyon v0.5.36
// 

package net.ender.cc.scoring.commands;

import net.ender.cc.scoring.Scoring;
import net.ender.cc.scoring.data.ScoringTeam;
import net.ender.cc.scoring.sql.SQLPlayer;
import net.ender.cc.scoring.util.Leaderboard;
import net.ender.cc.scoring.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerScoreCMD implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (args.length == 0) {
            if (Scoring.getInstance().getConfig().getBoolean("mysql.use-mysql") && sender instanceof Player) {
                SQLPlayer getter = new SQLPlayer((Player) sender);
                sender.sendMessage(Util.colorize("&aYour Current Score is &6" + getter.getScore()));
            }
            return true;
        } else if (args.length == 2 && sender instanceof Player) {
            String lowerCase4 = args[0].toLowerCase();
            String lowerCase5 = args[1].toLowerCase();
            SQLPlayer sqlPlayer = new SQLPlayer((Player) sender);
            if (lowerCase4.equals("top")) {
                switch (lowerCase5) {
                    case "teams":
                    case "team": {

                        sender.sendMessage(Util.colorize("&9================Leaderboard================"));
                        for (int i = 0; i < Leaderboard.entrySet().size(); i++) {
                            ScoringTeam team = Leaderboard.entrySet().get(i).getKey();
                            sender.sendMessage(Util.colorize("&e" + Util.ordinalSuffixOf(i + 1) + " Place. &c" + (sqlPlayer.getTeam() == team ? "&l" : "") + team.getFormattedName() + "&e With a Score of " + Leaderboard.entrySet().get(i).getValue()));
                        }
                        sender.sendMessage(Util.colorize("&9=========================================="));

                        break;
                    }
                    case "individual": {
                        sender.sendMessage(Util.colorize("&9================Leaderboard================"));
                        for (int i = 0; i < Leaderboard.entrySet2().size(); i++) {
                            sender.sendMessage(Util.colorize("&e" + (i + 1) + ". &c" + Leaderboard.entrySet2().get(i).getKey() + "&e With a Score of &a" + Leaderboard.entrySet2().get(i).getValue()));
                        }
                        sender.sendMessage(Util.colorize("&9=========================================="));
                        break;
                    }
                }
            }
        } else if (args.length == 3) {
            if (sender.hasPermission("scoring.admin")) {
                if (Scoring.getInstance().getConfig().getBoolean("mysql.use-mysql")) {
                    String lowerCase = args[0].toLowerCase();
                    switch (lowerCase) {
                        case "add":
                        case "give": {
                            if (args[1] == null) {
                                sender.sendMessage(Util.colorize("&cMissing arguments!"));
                                break;
                            }
                            Player target = Bukkit.getPlayer(args[1]);
                            sender.sendMessage(Util.colorize("&aAdded &6" + args[2] + " Score &ato &e" + target.getName()));
                            SQLPlayer targetDataHandler = new SQLPlayer(target);
                            targetDataHandler.setScore(targetDataHandler.getScore() + Integer.parseInt(args[2]));
                            break;
                        }
                        case "set": {
                            if (args[1] == null) {
                                sender.sendMessage(Util.colorize("&cMissing arguments!"));
                                break;
                            }
                            Player target = Bukkit.getPlayer(args[1]);
                            sender.sendMessage(Util.colorize("&aSet &e" + target.getName() + "&a's purse to &6" + args[2] + " coins&a."));
                            SQLPlayer targetDataHandler = new SQLPlayer(target);
                            targetDataHandler.setScore(Integer.parseInt(args[2]));
                            break;
                        }
                        case "remove": {
                            if (args[1] == null) {
                                sender.sendMessage(Util.colorize("&cMissing arguments!"));
                                break;
                            }
                            Player target = Bukkit.getPlayer(args[1]);
                            sender.sendMessage(Util.colorize("&aRemoved &6" + args[2] + " coins &afrom &e" + target.getName() + "&a's purse."));
                            SQLPlayer targetDataHandler = new SQLPlayer(target);
                            targetDataHandler.setScore(targetDataHandler.getScore() - Integer.parseInt(args[2]));
                            break;
                        }
                    }
                }
            } else {
                sender.sendMessage(Util.colorize("&cYou must be admin or higher to use this command."));
            }
        }
        return false;
    }
}
