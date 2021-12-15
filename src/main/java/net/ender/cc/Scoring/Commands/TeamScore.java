// 
// Decompiled by Procyon v0.5.36
// 

package net.ender.cc.Scoring.Commands;

import net.ender.cc.Scoring.sql.NonPlayerSQLGetter;
import net.ender.cc.Scoring.sql.SQLGetter;
import net.ender.cc.Scoring.Scoring;
import net.ender.cc.Scoring.util.Util;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;

public class TeamScore implements CommandExecutor
{
    public boolean onCommand(final CommandSender commandSender, final Command command, final String s, final String[] args) {
        final Player player = (Player)commandSender;
        if (args.length == 0) {
            final SQLGetter getter = new SQLGetter(player, Scoring.getInstance());
            final NonPlayerSQLGetter nonplayergetter = new NonPlayerSQLGetter(Scoring.getInstance());
            final String team = getter.getTeamName();
            final int teamScore = nonplayergetter.getTeamScore(team);
            player.sendMessage(Util.colorize("&aYou team has &6" + teamScore + " &apoints"));
        }else {
            if (args.length == 3 && player.hasPermission("scoring.admin") && Scoring.getInstance().getConfig().getBoolean("mysql.use-mysql")) {
                final String lowerCase = args[0].toLowerCase();
                switch (lowerCase) {
                    case "set": {
                        if (args[1] == null) {
                            player.sendMessage(Util.colorize("&cMissing arguments!"));
                            break;
                        }
                        final Player target = (Player)commandSender;
                        final NonPlayerSQLGetter targetDataHandler = new NonPlayerSQLGetter(Scoring.getInstance());
                        final String lowerCase5 = args[1].toLowerCase();
                        switch (lowerCase5) {
                            case "team_1":
                            case "team_2":
                            case "team_3":
                            case "team_4":
                            case "team_5":
                            case "team_6":
                            case "team_7":
                            case "team_8": {
                                player.sendMessage(Util.colorize("&aSet &6" + args[1] + " &ascore to &6" + args[2]));
                                targetDataHandler.setTeamScore(Integer.parseInt(args[2]), args[1]);
                                break;
                            }
                        }
                        break;
                    }
                    case "give": {
                        final NonPlayerSQLGetter targetDataHandler2 = new NonPlayerSQLGetter(Scoring.getInstance());
                        final String lowerCase6 = args[1].toLowerCase();
                        switch (lowerCase6) {
                            case "team_1":
                            case "team_2":
                            case "team_8":
                            case "team_7":
                            case "team_3":
                            case "team_4":
                            case "team_5":
                            case "team_6": {
                                player.sendMessage(Util.colorize("&aAdded &6" + args[2] + " Score &ato &e" + args[1]));
                                targetDataHandler2.setTeamScore(targetDataHandler2.getTeamScore(args[1]) + Integer.parseInt(args[2]), args[1]);
                                break;
                            }
                            default: {
                                break;
                            }
                        }
                    }
                    case "remove": {
                        final NonPlayerSQLGetter targetDataHandler2 = new NonPlayerSQLGetter(Scoring.getInstance());
                        final String lowerCase7 = args[1].toLowerCase();
                        switch (lowerCase7) {
                            case "team_1":
                            case "team_2":
                            case "team_3":
                            case "team_4":
                            case "team_5":
                            case "team_6":
                            case "team_7":
                            case "team_8": {
                                player.sendMessage(Util.colorize("&aRemoved &6" + args[2] + " Score &ato &e" + args[1]));
                                targetDataHandler2.setTeamScore(targetDataHandler2.getTeamScore(args[1]) - Integer.parseInt(args[2]), args[1]);
                                break;
                            }
                            default: {
                                break;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
