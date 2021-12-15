// 
// Decompiled by Procyon v0.5.36
// 

package net.ender.cc.Scoring.Commands;

import java.util.Iterator;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class TabCompleter implements org.bukkit.command.TabCompleter
{
    public List<String> onTabComplete(final CommandSender commandSender, final Command command, final String s, final String[] strings) {
        final List<String> completions = new ArrayList<String>();
        if (command.getName().equals("score")) {
            if (strings.length == 1) {
                completions.add("give");
                completions.add("set");
                completions.add("remove");
                completions.add("top");
                return completions;
            }
            final String lowerCase = strings[0].toLowerCase();
            switch (lowerCase) {
                case "set": {
                    if (strings.length == 2) {
                        for (final Player p : Bukkit.getOnlinePlayers()) {
                            completions.add(p.getPlayerListName());
                        }
                    }
                }
                case "remove": {
                    if (strings.length == 2) {
                        for (final Player p : Bukkit.getOnlinePlayers()) {
                            completions.add(p.getPlayerListName());
                        }
                    }
                }
                case "give": {
                    if (strings.length == 2) {
                        for (final Player p : Bukkit.getOnlinePlayers()) {
                            completions.add(p.getPlayerListName());
                        }
                    }
                }
                case "top": {
                    if (strings.length == 2) {
                        completions.add("teams");
                        completions.add("individual");
                    }
                }
            }
        }
        if (command.getName().equals("team")) {
            if (strings.length == 1) {
                completions.add("set");
                completions.add("reset");
                return completions;
            }
            if (strings.length == 2) {
                for (final Player p : Bukkit.getOnlinePlayers()) {
                    completions.add(p.getPlayerListName());
                }
            }
            final String lowerCase = strings[0].toLowerCase();
            switch (lowerCase) {
                case "set": {
                    if (strings.length == 3) {
                        completions.add("team_1(Green)");
                        completions.add("team_2(Blue)");
                        completions.add("team_3(Pink)");
                        completions.add("team_4(Brown) ");
                        completions.add("team_5(Red)");
                        completions.add("team_6(Black)");
                        completions.add("team_7(Purple)");
                        completions.add("team_8(Cyan)");
                        break;
                    }
                    break;
                }
            }
        }
        if (command.getName().equals("teamscore")) {
            if (strings.length == 1) {
                completions.add("set");
                completions.add("reset");
                completions.add("remove");
                return completions;
            }
            if (strings.length == 2) {
                completions.add("team_1");
                completions.add("team_2");
                completions.add("team_3");
                completions.add("team_4");
                completions.add("team_5");
                completions.add("team_6");
                completions.add("team_7");
                completions.add("team_8");
            }
        }
        return completions;
    }
}
