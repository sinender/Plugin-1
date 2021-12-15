// 
// Decompiled by Procyon v0.5.36
// 

package net.ender.cc.Scoring.Commands;

import net.ender.cc.Scoring.sql.NonPlayerSQLGetter;
import net.ender.cc.Scoring.util.Leaderboard;
import org.bukkit.Bukkit;
import net.ender.cc.Scoring.util.Util;
import net.ender.cc.Scoring.sql.SQLGetter;
import net.ender.cc.Scoring.Scoring;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;

import java.util.ArrayList;
import java.util.Locale;

public class PlayerScore implements CommandExecutor
{
    public boolean onCommand(final CommandSender commandSender, final Command command, final String s, final String[] args) {
        if (commandSender instanceof Player) {
            final Player player = (Player)commandSender;
            if (args.length == 0) {
                if (Scoring.getInstance().getConfig().getBoolean("mysql.use-mysql")) {
                    final SQLGetter getter = new SQLGetter(player, Scoring.getInstance());
                    player.sendMessage(Util.colorize("&aYour Current Score is &6" + getter.getScore()));
                }
                return true;
            }
            if(args.length == 2) {
                final String lowerCase4 = args[0].toLowerCase();
                final String lowerCase5 = args[1].toLowerCase();
                NonPlayerSQLGetter teamGetter = new NonPlayerSQLGetter(Scoring.getInstance());
                SQLGetter sqlGetter = new SQLGetter(player, Scoring.getInstance());
                switch (lowerCase4) {
                    case "top": {
                        switch (lowerCase5) {
                            case "teams":
                            case "team": {
                                player.sendMessage(Util.colorize("&9================Leaderboard================"));
                                if (sqlGetter.getTeamName().equalsIgnoreCase(Leaderboard.entrySet().get(0).getKey())) {
                                    player.sendMessage(Util.colorize("&e1st Place &c&l" + teamGetter.getTeamName(Leaderboard.entrySet().get(0).getKey()) + " &eWith a Score of " + Leaderboard.entrySet().get(0).getValue()));
                                } else {
                                    player.sendMessage(Util.colorize("&e1st Place &c" + teamGetter.getTeamName(Leaderboard.entrySet().get(0).getKey()) + " &eWith a Score of " + Leaderboard.entrySet().get(0).getValue()));
                                }
                                if (sqlGetter.getTeamName().equalsIgnoreCase(Leaderboard.entrySet().get(1).getKey())) {
                                    player.sendMessage(Util.colorize("&e2nd Place &c&l" + teamGetter.getTeamName(Leaderboard.entrySet().get(1).getKey()) + " &eWith a Score of " + Leaderboard.entrySet().get(1).getValue()));
                                } else {
                                    player.sendMessage(Util.colorize("&e2nd Place &c" + teamGetter.getTeamName(Leaderboard.entrySet().get(1).getKey()) + " &eWith a Score of " + Leaderboard.entrySet().get(1).getValue()));
                                }
                                if (sqlGetter.getTeamName().equalsIgnoreCase(Leaderboard.entrySet().get(2).getKey())) {
                                    player.sendMessage(Util.colorize("&e3rd Place &c&l" + teamGetter.getTeamName(Leaderboard.entrySet().get(2).getKey()) + " &eWith a Score of " + Leaderboard.entrySet().get(2).getValue()));
                                } else {
                                    player.sendMessage(Util.colorize("&e3rd Place &c" + teamGetter.getTeamName(Leaderboard.entrySet().get(2).getKey()) + " &eWith a Score of " + Leaderboard.entrySet().get(2).getValue()));
                                }
                                if (sqlGetter.getTeamName().equalsIgnoreCase(Leaderboard.entrySet().get(3).getKey())) {
                                    player.sendMessage(Util.colorize("&e4th Place &c&l" + teamGetter.getTeamName(Leaderboard.entrySet().get(3).getKey()) + " &eWith a Score of " + Leaderboard.entrySet().get(3).getValue()));
                                } else {
                                    player.sendMessage(Util.colorize("&e4th Place &c" + teamGetter.getTeamName(Leaderboard.entrySet().get(3).getKey()) + " &eWith a Score of " + Leaderboard.entrySet().get(3).getValue()));
                                }
                                if (sqlGetter.getTeamName().equalsIgnoreCase(Leaderboard.entrySet().get(4).getKey())) {
                                    player.sendMessage(Util.colorize("&e5th Place &c&l" + teamGetter.getTeamName(Leaderboard.entrySet().get(4).getKey()) + " &eWith a Score of " + Leaderboard.entrySet().get(4).getValue()));
                                } else {
                                    player.sendMessage(Util.colorize("&e5th Place &c" + teamGetter.getTeamName(Leaderboard.entrySet().get(4).getKey()) + " &eWith a Score of " + Leaderboard.entrySet().get(4).getValue()));
                                }
                                if (sqlGetter.getTeamName().equalsIgnoreCase(Leaderboard.entrySet().get(5).getKey())) {
                                    player.sendMessage(Util.colorize("&e6th Place &c&l" + teamGetter.getTeamName(Leaderboard.entrySet().get(5).getKey()) + " &eWith a Score of " + Leaderboard.entrySet().get(5).getValue()));
                                } else {
                                    player.sendMessage(Util.colorize("&e6th Place &c" + teamGetter.getTeamName(Leaderboard.entrySet().get(5).getKey()) + " &eWith a Score of " + Leaderboard.entrySet().get(5).getValue()));
                                }
                                if (sqlGetter.getTeamName().equalsIgnoreCase(Leaderboard.entrySet().get(6).getKey())) {
                                    player.sendMessage(Util.colorize("&e7th Place &c&l" + teamGetter.getTeamName(Leaderboard.entrySet().get(6).getKey()) + " &eWith a Score of " + Leaderboard.entrySet().get(6).getValue()));
                                } else {
                                    player.sendMessage(Util.colorize("&e7th Place &c" + teamGetter.getTeamName(Leaderboard.entrySet().get(6).getKey()) + " &eWith a Score of " + Leaderboard.entrySet().get(6).getValue()));
                                }
                                if (sqlGetter.getTeamName().equalsIgnoreCase(Leaderboard.entrySet().get(7).getKey())) {
                                    player.sendMessage(Util.colorize("&e8th Place &c&l" + teamGetter.getTeamName(Leaderboard.entrySet().get(7).getKey()) + " &eWith a Score of " + Leaderboard.entrySet().get(7).getValue()));
                                } else {
                                    player.sendMessage(Util.colorize("&e8th Place &c" + teamGetter.getTeamName(Leaderboard.entrySet().get(7).getKey()) + " &eWith a Score of " + Leaderboard.entrySet().get(7).getValue()));
                                }
                                player.sendMessage(Util.colorize("&9=========================================="));
                            }
                            case "individual" : {
                                player.sendMessage(Util.colorize("&9================Leaderboard================"));
                                for (int i = 0; i < Leaderboard.entrySet2().size(); i++) {
                                    player.sendMessage(Util.colorize("&e"+ (i+1) + ". &c" + Leaderboard.entrySet2().get(i).getKey() + "&e With a Score of &a" + Leaderboard.entrySet2().get(i).getValue()));
                                }
                                player.sendMessage(Util.colorize("&9=========================================="));
                                break;
                            }
                        }
                    }
                }
            }
            if (args.length == 3) {
                if (player.hasPermission("scoring.admin")) {
                    if (Scoring.getInstance().getConfig().getBoolean("mysql.use-mysql")) {
                        final String lowerCase3;
                        final String lowerCase = lowerCase3 = args[0].toLowerCase();
                        switch (lowerCase3) {
                            case "add": {
                                if (args[1] == null) {
                                    player.sendMessage(Util.colorize("&cMissing arguments!"));
                                    break;
                                }
                                final Player target = Bukkit.getPlayer(args[1]);
                                player.sendMessage(Util.colorize("&aAdded &6" + args[2] + " Score &ato &e" + target.getName()));
                                final SQLGetter targetDataHandler = new SQLGetter(target, Scoring.getInstance());
                                targetDataHandler.setScore(targetDataHandler.getScore() + Integer.parseInt(args[2]));
                                break;
                            }
                            case "give": {
                                if (args[1] == null) {
                                    player.sendMessage(Util.colorize("&cMissing arguments!"));
                                    break;
                                }
                                final Player target = Bukkit.getPlayer(args[1]);
                                player.sendMessage(Util.colorize("&aAdded &6" + args[2] + " Score &ato &e" + target.getName()));
                                final SQLGetter targetDataHandler = new SQLGetter(target, Scoring.getInstance());
                                targetDataHandler.setScore(targetDataHandler.getScore() + Integer.parseInt(args[2]));
                                break;
                            }
                            case "set": {
                                if (args[1] == null) {
                                    player.sendMessage(Util.colorize("&cMissing arguments!"));
                                    break;
                                }
                                final Player target = Bukkit.getPlayer(args[1]);
                                player.sendMessage(Util.colorize("&aSet &e" + target.getName() + "&a's purse to &6" + args[2] + " coins&a."));
                                final SQLGetter targetDataHandler = new SQLGetter(target, Scoring.getInstance());
                                targetDataHandler.setScore(Integer.parseInt(args[2]));
                                break;
                            }
                            case "remove": {
                                if (args[1] == null) {
                                    player.sendMessage(Util.colorize("&cMissing arguments!"));
                                    break;
                                }
                                final Player target = Bukkit.getPlayer(args[1]);
                                player.sendMessage(Util.colorize("&aRemoved &6" + args[2] + " coins &afrom &e" + target.getName() + "&a's purse."));
                                final SQLGetter targetDataHandler = new SQLGetter(target, Scoring.getInstance());
                                targetDataHandler.setScore(targetDataHandler.getScore() - Integer.parseInt(args[2]));
                                break;
                            }
                        }
                    }
                }
                else {
                    player.sendMessage(Util.colorize("&cYou must be admin or higher to use this command."));
                }
            }
        }
        else {
            if (args.length == 0) {
                Bukkit.getConsoleSender().sendMessage(Util.colorize("Missing Arguments"));
            }
            if (args.length == 1) {
                final Player target2 = Bukkit.getPlayer(args[0]);
                final SQLGetter getter = new SQLGetter(target2, Scoring.getInstance());
                Bukkit.getConsoleSender().sendMessage(Util.colorize(target2.getPlayerListName() + " Score is " + getter.getScore()));
            }
            if (args.length == 3 && Scoring.getInstance().getConfig().getBoolean("mysql.use-mysql")) {
                final String lowerCase4;
                final String lowerCase2 = lowerCase4 = args[0].toLowerCase();
                switch (lowerCase4) {
                    case "add": {
                        if (args[1] == null) {
                            Bukkit.getConsoleSender().sendMessage(Util.colorize("Missing arguments!"));
                            break;
                        }
                        final Player target3 = Bukkit.getPlayer(args[1]);
                        Bukkit.getConsoleSender().sendMessage(Util.colorize("Added " + args[2] + " Score to " + target3.getName()));
                        final SQLGetter targetDataHandler2 = new SQLGetter(target3, Scoring.getInstance());
                        targetDataHandler2.setScore(targetDataHandler2.getScore() + Integer.parseInt(args[2]));
                        break;
                    }
                    case "give": {
                        if (args[1] == null) {
                            Bukkit.getConsoleSender().sendMessage(Util.colorize("Missing arguments!"));
                            break;
                        }
                        final Player target3 = Bukkit.getPlayer(args[1]);
                        Bukkit.getConsoleSender().sendMessage(Util.colorize("Added " + args[2] + " Score to " + target3.getName() + "."));
                        final SQLGetter targetDataHandler2 = new SQLGetter(target3, Scoring.getInstance());
                        targetDataHandler2.setScore(targetDataHandler2.getScore() + Integer.parseInt(args[2]));
                        break;
                    }
                    case "set": {
                        if (args[1] == null) {
                            Bukkit.getConsoleSender().sendMessage(Util.colorize("Missing arguments!"));
                            break;
                        }
                        final Player target3 = Bukkit.getPlayer(args[1]);
                        Bukkit.getConsoleSender().sendMessage(Util.colorize("Set " + target3.getName() + "'s Score to " + args[2] + "."));
                        final SQLGetter targetDataHandler2 = new SQLGetter(target3, Scoring.getInstance());
                        targetDataHandler2.setScore(Integer.parseInt(args[2]));
                        break;
                    }
                    case "remove": {
                        if (args[1] == null) {
                            Bukkit.getConsoleSender().sendMessage(Util.colorize("Missing arguments!"));
                            break;
                        }
                        final Player target3 = Bukkit.getPlayer(args[1]);
                        Bukkit.getConsoleSender().sendMessage(Util.colorize("Removed " + args[2] + " Score from " + target3.getName() + "."));
                        final SQLGetter targetDataHandler2 = new SQLGetter(target3, Scoring.getInstance());
                        targetDataHandler2.setScore(targetDataHandler2.getScore() - Integer.parseInt(args[2]));
                        break;
                    }
                }
            }
        }
        return false;
    }
}
