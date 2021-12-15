// 
// Decompiled by Procyon v0.5.36
// 

package net.ender.cc.Scoring.Commands;

import org.bukkit.ChatColor;
import org.bukkit.Bukkit;
import net.ender.cc.Scoring.util.Util;
import net.ender.cc.Scoring.sql.SQLGetter;
import net.ender.cc.Scoring.Scoring;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;

public class PlayerTeamCMD implements CommandExecutor
{
    public boolean onCommand(final CommandSender commandSender, final Command command, final String s, final String[] args) {
        final Player player = (Player)commandSender;
        if (commandSender instanceof Player) {
            if (args.length == 0) {
                final SQLGetter getter = new SQLGetter(player, Scoring.getInstance());
                final String teamName = getter.getTeamName();
                player.sendMessage(Util.colorize("&aYou are on team &6" + teamName));
            }
            if (args.length == 3) {
                if (player.hasPermission("scoring.admin")) {
                    if (Scoring.getInstance().getConfig().getBoolean("mysql.use-mysql")) {
                        final String lowerCase7;
                        final String lowerCase = lowerCase7 = args[0].toLowerCase();
                        switch (lowerCase7) {
                            case "set": {
                                if (args[1] == null) {
                                    player.sendMessage(Util.colorize("&cMissing arguments!"));
                                    break;
                                }
                                final Player target = Bukkit.getPlayer(args[1]);
                                final SQLGetter targetDataHandler = new SQLGetter(target, Scoring.getInstance());
                                final String lowerCase8;
                                final String lowerCase2 = lowerCase8 = args[2].toLowerCase();
                                switch (lowerCase8) {
                                    case "team_1(green)": {
                                        player.sendMessage(Util.colorize(ChatColor.GREEN + target.getName() + "&a's team got set to &6" + args[2]));
                                        targetDataHandler.setTeamName("team_1");
                                        break;
                                    }
                                    case "team_2(blue)": {
                                        player.sendMessage(Util.colorize(ChatColor.GREEN + target.getName() + "&a's team got set to &6" + args[2]));
                                        targetDataHandler.setTeamName("team_2");
                                        break;
                                    }
                                    case "team_3(pink)": {
                                        player.sendMessage(Util.colorize(ChatColor.GREEN + target.getName() + "&a's team got set to &6" + args[2]));
                                        targetDataHandler.setTeamName("team_3");
                                        break;
                                    }
                                    case "team_4(brown)": {
                                        player.sendMessage(Util.colorize(ChatColor.GREEN + target.getName() + "&a's team got set to &6" + args[2]));
                                        targetDataHandler.setTeamName("team_4");
                                        break;
                                    }
                                    case "team_5(red)": {
                                        player.sendMessage(Util.colorize(ChatColor.GREEN + target.getName() + "&a's team got set to &6" + args[2]));
                                        targetDataHandler.setTeamName("team_5");
                                        break;
                                    }
                                    case "team_6(black)": {
                                        player.sendMessage(Util.colorize(ChatColor.GREEN + target.getName() + "&a's team got set to &6" + args[2]));
                                        targetDataHandler.setTeamName("team_6");
                                        break;
                                    }
                                    case "team_7(purple)": {
                                        player.sendMessage(Util.colorize(ChatColor.GREEN + target.getName() + "&a's team got set to &6" + args[2]));
                                        targetDataHandler.setTeamName("team_7");
                                        break;
                                    }
                                    case "team_8(cyan)": {
                                        player.sendMessage(Util.colorize(ChatColor.GREEN + target.getName() + "&a's team got set to &6" + args[2]));
                                        targetDataHandler.setTeamName("team_8");
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                    }
                }
                else {
                    player.sendMessage(Util.colorize("&cYou must be admin or higher to use this command."));
                }
            }
            if (args.length == 2) {
                if (player.hasPermission("scoring.admin")) {
                    if (Scoring.getInstance().getConfig().getBoolean("mysql.use-mysql")) {
                        final String lowerCase9;
                        final String lowerCase3 = lowerCase9 = args[0].toLowerCase();
                        switch (lowerCase9) {
                            case "reset": {
                                if (args[1] == null) {
                                    player.sendMessage(Util.colorize("&cMissing arguments!"));
                                    break;
                                }
                                final Player target = Bukkit.getPlayer(args[1]);
                                final SQLGetter targetDataHandler = new SQLGetter(target, Scoring.getInstance());
                                player.sendMessage(Util.colorize("&cRemoved &6" + target.getName() + "&6's &cTeam"));
                                targetDataHandler.setTeamName("NONE");
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
                final SQLGetter getter2 = new SQLGetter(target2, Scoring.getInstance());
                Bukkit.getConsoleSender().sendMessage(Util.colorize(target2.getPlayerListName() + " Score is " + getter2.getScore()));
            }
            if (args.length == 3 && Scoring.getInstance().getConfig().getBoolean("mysql.use-mysql")) {
                final String lowerCase10;
                final String lowerCase4 = lowerCase10 = args[0].toLowerCase();
                switch (lowerCase10) {
                    case "set": {
                        if (args[1] == null) {
                            Bukkit.getConsoleSender().sendMessage(Util.colorize("&cMissing arguments!"));
                            break;
                        }
                        final Player target = Bukkit.getPlayer(args[1]);
                        final SQLGetter targetDataHandler = new SQLGetter(target, Scoring.getInstance());
                        final String lowerCase11;
                        final String lowerCase5 = lowerCase11 = args[2].toLowerCase();
                        switch (lowerCase11) {
                            case "team_1": {
                                Bukkit.getConsoleSender().sendMessage(Util.colorize(ChatColor.GREEN + target.getName() + "&a's team got set to &6" + args[2]));
                                targetDataHandler.setTeamName(args[2]);
                            }
                            case "team_2": {
                                Bukkit.getConsoleSender().sendMessage(Util.colorize(ChatColor.GREEN + target.getName() + "&a's team got set to &6" + args[2]));
                                targetDataHandler.setTeamName(args[2]);
                            }
                            case "team_3": {
                                Bukkit.getConsoleSender().sendMessage(Util.colorize(ChatColor.GREEN + target.getName() + "&a's team got set to &6" + args[2]));
                                targetDataHandler.setTeamName(args[2]);
                            }
                            case "team_4": {
                                Bukkit.getConsoleSender().sendMessage(Util.colorize(ChatColor.GREEN + target.getName() + "&a's team got set to &6" + args[2]));
                                targetDataHandler.setTeamName(args[2]);
                            }
                            case "team_5": {
                                Bukkit.getConsoleSender().sendMessage(Util.colorize(ChatColor.GREEN + target.getName() + "&a's team got set to &6" + args[2]));
                                targetDataHandler.setTeamName(args[2]);
                            }
                            case "team_6": {
                                Bukkit.getConsoleSender().sendMessage(Util.colorize(ChatColor.GREEN + target.getName() + "&a's team got set to &6" + args[2]));
                                targetDataHandler.setTeamName(args[2]);
                            }
                            case "team_7": {
                                Bukkit.getConsoleSender().sendMessage(Util.colorize(ChatColor.GREEN + target.getName() + "&a's team got set to &6" + args[2]));
                                targetDataHandler.setTeamName(args[2]);
                            }
                            case "team_8": {
                                Bukkit.getConsoleSender().sendMessage(Util.colorize(ChatColor.GREEN + target.getName() + "&a's team got set to &6" + args[2]));
                                targetDataHandler.setTeamName(args[2]);
                                break;
                            }
                        }
                        break;
                    }
                }
            }
            if (args.length == 2 && Scoring.getInstance().getConfig().getBoolean("mysql.use-mysql")) {
                final String lowerCase12;
                final String lowerCase6 = lowerCase12 = args[0].toLowerCase();
                switch (lowerCase12) {
                    case "reset": {
                        if (args[1] == null) {
                            Bukkit.getConsoleSender().sendMessage(Util.colorize("&cMissing arguments!"));
                            break;
                        }
                        final Player target = Bukkit.getPlayer(args[1]);
                        final SQLGetter targetDataHandler = new SQLGetter(target, Scoring.getInstance());
                        Bukkit.getConsoleSender().sendMessage(Util.colorize("&cRemoved &6" + target.getName() + "&6's &cTeam"));
                        targetDataHandler.setTeamName("NONE");
                        break;
                    }
                }
            }
        }
        return false;
    }
}
