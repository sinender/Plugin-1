package net.ender.cc.scoring.data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.ender.cc.scoring.util.Util;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum ScoringTeam {
    EMERALD_ENDERMEN,
    INDIGO_ILLAGERS,
    FUSCHIA_PHANTOMS,
    BRONZE_BLAZES,
    CRIMSON_CREEPERS,
    CHARCOAL_CHICKENS,
    VIOLET_VEXES,
    COBALT_CREEPERS,
    ;

    public static ScoringTeam fromName(String teamName) {
        for (ScoringTeam scoringTeam : values()) {
            if (scoringTeam.getFormattedName().equalsIgnoreCase(teamName))
                return scoringTeam;
        }

        return null;
    }

    public static boolean anyMatch(String name) {
        return Arrays.stream(values()).anyMatch((scoringTeam -> name.toUpperCase().equals(scoringTeam.name())));
    }

    public String getFormattedName() {
        return Util.capitalise(name().toLowerCase().split("_"), "_");
    }

}
