// 
// Decompiled by Procyon v0.5.36
// 

package net.ender.cc.scoring.util;

import net.ender.cc.scoring.data.ScoringTeam;
import net.ender.cc.scoring.sql.SQLPlayer;
import net.ender.cc.scoring.sql.SQLTeam;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Leaderboard {
    public static LinkedHashMap<ScoringTeam, Integer> teamScoresSorted() {
        Map<ScoringTeam, Integer> map = new HashMap<>();

        for (ScoringTeam team : ScoringTeam.values()) {
            SQLTeam teamGetter = new SQLTeam();
            int score = teamGetter.getTeamScore(team);
            map.put(team, score);
        }

        return Util.getSortedMap(map);
    }

    public static LinkedHashMap<String, Integer> playerScoresSorted() {
        Map<String, Integer> map = new HashMap<>();

        for (Player player : Bukkit.getOnlinePlayers()) {
            SQLPlayer getter = new SQLPlayer(player);
            map.put(player.getPlayerListName(), getter.getScore());
        }

        return Util.getSortedMap(map);
    }

    //but why??

    public static ArrayList<Map.Entry<ScoringTeam, Integer>> entrySet() {
        return new ArrayList<>(teamScoresSorted().entrySet());
    }

    public static ArrayList<Map.Entry<String, Integer>> entrySet2() {
        return new ArrayList<>(playerScoresSorted().entrySet());
    }

    public static int teamPos(ScoringTeam team) {
        for (int i = 0; i <= 7; i++) {
            ScoringTeam key = Leaderboard.entrySet().get(i).getKey();
            if (key == team) {
                return (i + 1);
            }
        }
        return 0;
    }
}
