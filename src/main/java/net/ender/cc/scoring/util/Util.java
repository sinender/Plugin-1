package net.ender.cc.scoring.util;

import net.ender.cc.scoring.Scoring;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class Util {
    public static String colorize(final String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static <T> LinkedHashMap<T, Integer> getSortedMap(Map<T, Integer> input) {
        return input.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
    }

    public static String capitalise(String[] arr, String delim) {
        StringBuilder builder = new StringBuilder();

        for (String str : arr) {
            builder.append(str.substring(0, 1).toUpperCase())
                    .append(str.substring(1).toLowerCase())
                    .append(delim);
        }

        return builder.substring(0, builder.length() - delim.length());
    }

    public static int getRandomInteger(final int max) {
        final Random ran = new Random();
        return ran.nextInt(max);
    }

    public static void scheduleTask(final Runnable run, final int i) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(Scoring.getInstance(), run, i);
    }

    public static String ordinalSuffixOf(int i) {
        int j = i % 10;
        int k = i % 100;

        if (j == 1 && k != 11) {
            return i + "st";
        } else if (j == 2 && k != 12) {
            return i + "nd";
        } else if (j == 3 && k != 13) {
            return i + "rd";
        }

        return i + "th";
    }
}
