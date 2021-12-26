// 
// Decompiled by Procyon v0.5.36
// 

package net.ender.cc.scoring.util;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.ender.cc.scoring.Scoring;
import net.ender.cc.scoring.data.ScoringTeam;
import net.ender.cc.scoring.sql.SQLPlayer;
import net.ender.cc.scoring.sql.SQLTeam;
import org.bukkit.entity.Player;

public class PlaceholderExtension extends PlaceholderExpansion {
    public boolean persist() {
        return true;
    }

    public String getIdentifier() {
        return "scoring";
    }

    public String getAuthor() {
        return "Ender";
    }

    public String getVersion() {
        return Scoring.getInstance().getDescription().getVersion();
    }

    public boolean canRegister() {
        return true;
    }

    public String onPlaceholderRequest(Player p, String identifier) {
        if (p == null) {
            return "";
        }
        SQLTeam teamGetter = new SQLTeam();
        SQLPlayer playerGetter = new SQLPlayer(p);

        for (ScoringTeam team : ScoringTeam.values()) {
            if (identifier.equalsIgnoreCase(team.name() + "_SCORE")) {
                return teamGetter.getTeamScore(team) + "";
            }
        }

        if (identifier.equalsIgnoreCase("player_team_score")) {
            if (playerGetter.getTeam() == null) {
                return "0";
            }

            return "" + teamGetter.getTeamScore(playerGetter.getTeam());
        } else {
            if (identifier.equalsIgnoreCase("player_score")) {
                return "" + playerGetter.getScore();
            }
            if (identifier.equalsIgnoreCase("player_team")) {
                return "" + playerGetter.getTeam().getFormattedName();
            }

            for (int i = 0; i < Leaderboard.entrySet().size(); i++) {
                if (identifier.equalsIgnoreCase("place_" + (i + 1) + "_team")) {
                    return Leaderboard.entrySet().get(i).getValue() + "";
                }
            }


            if (identifier.equalsIgnoreCase("team_position")) {
                for (int i = 0; i <= 7; i++) {
                    ScoringTeam key = Leaderboard.entrySet().get(i).getKey();
                    int Score = teamGetter.getTeamScore(key);
                    if (key == playerGetter.getTeam()) {
                        return Util.colorize("&f" + Util.ordinalSuffixOf(i + 1) + " Place. &c&l" + teamGetter.getLeaderboardTeamName(key) + "&f" + Score);
                    }
                }
            }
            return null;
        }
    }
}
