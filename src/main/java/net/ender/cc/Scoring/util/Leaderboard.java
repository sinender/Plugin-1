// 
// Decompiled by Procyon v0.5.36
// 

package net.ender.cc.Scoring.util;

import java.util.*;

import net.ender.cc.Scoring.sql.NonPlayerSQLGetter;
import net.ender.cc.Scoring.Scoring;
import net.ender.cc.Scoring.sql.SQLGetter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Leaderboard
{
    private final Scoring plugin;
    
    public Leaderboard(final Scoring plugin) {
        this.plugin = plugin;
    }
    
    public static LinkedHashMap<String, Integer> Sorted() {
        final List<String> allTeams = Arrays.asList("team_1", "team_2", "team_3", "team_4", "team_5", "team_6", "team_7", "team_8");
        int score = 0;
        final Map<String, Integer> map = new HashMap<String, Integer>();
        for (final String teams : allTeams) {
            final NonPlayerSQLGetter teamGetter = new NonPlayerSQLGetter(Scoring.getInstance());
            score = teamGetter.getTeamScore(teams);
            map.put(teams, score);
        }
        return Util.getSortedMap(map);
    }

    public static LinkedHashMap<String, Integer> Sorted2() {
        int score = 0;
        final Map<String, Integer> map = new HashMap<String, Integer>();
        for (final Player players : Bukkit.getOnlinePlayers()) {
            SQLGetter getter = new SQLGetter(players,Scoring.getInstance());
            score = getter.getScore();
            map.put(players.getPlayerListName(), score);
        }
        return Util.getSortedMap(map);
    }
    
    public static ArrayList<Map.Entry<String, Integer>> entrySet() {
        final ArrayList<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(Sorted().entrySet());
        return list;
    }
    public static ArrayList<Map.Entry<String, Integer>> entrySet2() {
        final ArrayList<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(Sorted2().entrySet());
        return list;
    }

    public static int teamPos(String team) {
        for (int i = 0; i <= 7; i++) {
            String key = Leaderboard.entrySet().get(i).getKey();
            if (key.equalsIgnoreCase(team)) {
                return (i + 1);
            }
        }
        return 0;
    }
}
