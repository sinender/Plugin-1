// 
// Decompiled by Procyon v0.5.36
// 

package net.ender.cc.scoring.commands;

import net.ender.cc.scoring.Scoring;
import net.ender.cc.scoring.data.ScoringTeam;
import net.ender.cc.scoring.sql.SQLPlayer;
import net.ender.cc.scoring.sql.SQLTeam;
import net.ender.cc.scoring.util.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeamScoreCMD implements CommandExecutor {
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player player = (Player) commandSender;
        if (args.length == 0) {
            SQLPlayer getter = new SQLPlayer(player);
            SQLTeam sqlTeam = new SQLTeam();
            ScoringTeam team = getter.getTeam();

            int teamScore = sqlTeam.getTeamScore(team);

            player.sendMessage(Util.colorize("&aYou team has &6" + teamScore + " &apoints"));
        } else {
            if (args.length == 3 && player.hasPermission("scoring.admin") && Scoring.getInstance().getConfig().getBoolean("mysql.use-mysql")) {
                String lowerCase = args[0].toLowerCase();
                switch (lowerCase) {
                    case "set": {
                        if (args[1] == null) {
                            player.sendMessage(Util.colorize("&cMissing arguments!"));
                            break;
                        }
                        SQLTeam targetDataHandler = new SQLTeam();
                        if (ScoringTeam.anyMatch(args[1])) {
                            ScoringTeam team = ScoringTeam.valueOf(args[1].toUpperCase());
                            targetDataHandler.setTeamScore(Integer.parseInt(args[2]), team);
                            player.sendMessage(Util.colorize("&aSet &6" + team.name() + " &ascore to &6" + args[2]));
                        }

                        break;
                    }

                    case "give": {
                        SQLTeam targetDataHandler2 = new SQLTeam();

                        if (ScoringTeam.anyMatch(args[1])) {
                            ScoringTeam team = ScoringTeam.valueOf(args[1].toUpperCase());
                            targetDataHandler2.setTeamScore(targetDataHandler2.getTeamScore(team) + Integer.parseInt(args[2]), team);
                            player.sendMessage(Util.colorize("&aAdded &6" + args[2] + " Score &ato &e" + args[1]));
                        }
                        break;
                    }

                    case "remove": {
                        if (ScoringTeam.anyMatch(args[1])) {
                            SQLTeam targetDataHandler2 = new SQLTeam();
                            ScoringTeam team = ScoringTeam.valueOf(args[1].toUpperCase());

                            targetDataHandler2.setTeamScore(targetDataHandler2.getTeamScore(team) - Integer.parseInt(args[2]), team);
                            player.sendMessage(Util.colorize("&aRemoved &6" + args[2] + " Score &ato &e" + team.name()));
                        }

                        break;
                    }
                }
            }
        }
        return false;
    }
}
