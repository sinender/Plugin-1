// 
// Decompiled by Procyon v0.5.36
// 

package net.ender.cc.Scoring.util;

import net.ender.cc.Scoring.sql.SQLGetter;
import net.ender.cc.Scoring.sql.NonPlayerSQLGetter;
import org.bukkit.entity.Player;
import net.ender.cc.Scoring.Scoring;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;

import java.util.Map;

public class PlaceHolderAPI extends PlaceholderExpansion
{
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
    
    public String onPlaceholderRequest(final Player p, final String identifier) {
        if (p == null) {
            return "";
        }
        final NonPlayerSQLGetter teamGetter = new NonPlayerSQLGetter(Scoring.getInstance());
        final SQLGetter playerGetter = new SQLGetter(p, Scoring.getInstance());
        if (identifier.equalsIgnoreCase("team_1_score")) {
            return "" + teamGetter.getTeamScore("team_1");
        }
        if (identifier.equalsIgnoreCase("team_2_score")) {
            return "" + teamGetter.getTeamScore("team_2");
        }
        if (identifier.equalsIgnoreCase("team_3_score")) {
            return "" + teamGetter.getTeamScore("team_3");
        }
        if (identifier.equalsIgnoreCase("team_4_score")) {
            return "" + teamGetter.getTeamScore("team_4");
        }
        if (identifier.equalsIgnoreCase("team_5_score")) {
            return "" + teamGetter.getTeamScore("team_5");
        }
        if (identifier.equalsIgnoreCase("team_6_score")) {
            return "" + teamGetter.getTeamScore("team_6");
        }
        if (identifier.equalsIgnoreCase("team_7_score")) {
            return "" + teamGetter.getTeamScore("team_7");
        }
        if (identifier.equalsIgnoreCase("team_8_score")) {
            return "" + teamGetter.getTeamScore("team_8");
        }
        if (identifier.equalsIgnoreCase("player_team_score")) {
            if (playerGetter.getTeamName().equalsIgnoreCase("none")) {
                return "0";
            }
            return "" + teamGetter.getTeamScore(playerGetter.getTeamName());
        }
        else {
            if (identifier.equalsIgnoreCase("player_score")) {
                return "" + playerGetter.getScore();
            }
            if (identifier.equalsIgnoreCase("player_team")) {
                return "" + teamGetter.getTeamName(playerGetter.getTeamName());
            }
            if (identifier.equalsIgnoreCase("first_place_team")) {
                return "" + Leaderboard.entrySet().get(0).getValue();
            }
            if (identifier.equalsIgnoreCase("second_place_team")) {
                return "" + Leaderboard.entrySet().get(1).getValue();
            }
            if (identifier.equalsIgnoreCase("third_place_team")) {
                return "" + Leaderboard.entrySet().get(2).getValue();
            }
            if (identifier.equalsIgnoreCase("fourth_place_team")) {
                return "" + Leaderboard.entrySet().get(3).getValue();
            }
            if (identifier.equalsIgnoreCase("fifth_place_team")) {
                return "" + Leaderboard.entrySet().get(4).getValue();
            }
            if (identifier.equalsIgnoreCase("sixth_place_team")) {
                return "" + Leaderboard.entrySet().get(5).getValue();
            }
            if (identifier.equalsIgnoreCase("seventh_place_team")) {
                return "" + Leaderboard.entrySet().get(6).getValue();
            }
            if (identifier.equalsIgnoreCase("eighth_place_team")) {
                return "" + Leaderboard.entrySet().get(7).getValue();
            }
            if (identifier.equalsIgnoreCase("first_place_team_name")) {
                if(playerGetter.getTeamName().equalsIgnoreCase(  Leaderboard.entrySet().get(0).getKey())){
                    return "&c&l" + teamGetter.getLeaderboardTeamName(Leaderboard.entrySet().get(0).getKey());
                }
                return "" + teamGetter.getLeaderboardTeamName(Leaderboard.entrySet().get(0).getKey());
            }
            if (identifier.equalsIgnoreCase("second_place_team_name")) {
                if(playerGetter.getTeamName().equalsIgnoreCase(  Leaderboard.entrySet().get(1).getKey())){
                    return "&c&l" + teamGetter.getLeaderboardTeamName(Leaderboard.entrySet().get(1).getKey());
                }
                return "" + teamGetter.getLeaderboardTeamName(Leaderboard.entrySet().get(1).getKey());
            }
            if (identifier.equalsIgnoreCase("third_place_team_name")) {
                if(playerGetter.getTeamName().equalsIgnoreCase( Leaderboard.entrySet().get(2).getKey())){
                    return "&c&l" + teamGetter.getLeaderboardTeamName(Leaderboard.entrySet().get(2).getKey());
                }
                return "" + teamGetter.getLeaderboardTeamName(Leaderboard.entrySet().get(2).getKey());
            }
            if (identifier.equalsIgnoreCase("fourth_place_team_name")) {
                return "" + teamGetter.getLeaderboardTeamName(Leaderboard.entrySet().get(3).getKey());
            }
            if (identifier.equalsIgnoreCase("fifth_place_team_name")) {
                return "" + teamGetter.getLeaderboardTeamName(Leaderboard.entrySet().get(4).getKey());
            }
            if (identifier.equalsIgnoreCase("sixth_place_team_name")) {
                return "" + teamGetter.getLeaderboardTeamName(Leaderboard.entrySet().get(5).getKey());
            }
            if (identifier.equalsIgnoreCase("seventh_place_team_name")) {
                return "" + teamGetter.getLeaderboardTeamName(Leaderboard.entrySet().get(6).getKey());
            }
            if (identifier.equalsIgnoreCase("eighth_place_team_name")) {
                return "" + teamGetter.getLeaderboardTeamName(Leaderboard.entrySet().get(7).getKey());
            }
            if (identifier.equalsIgnoreCase("team_position")){
                for (int i = 0; i <= 7; i++) {
                    String key = Leaderboard.entrySet().get(i).getKey();
                    int Score = teamGetter.getTeamScore(key);
                    if(key.equalsIgnoreCase(playerGetter.getTeamName())){
                        if(key.equalsIgnoreCase(Leaderboard.entrySet().get(0).getKey()) || key.equalsIgnoreCase(Leaderboard.entrySet().get(1).getKey()) || key.equalsIgnoreCase(Leaderboard.entrySet().get(2).getKey())){
                            return "";
                        }else {
                            return Util.colorize("&f" + (i + 1) + ". &c&l" + teamGetter.getLeaderboardTeamName(key) + "&f" + Score);
                        }
                    }

                }
            }
            return null;
        }
    }
}
