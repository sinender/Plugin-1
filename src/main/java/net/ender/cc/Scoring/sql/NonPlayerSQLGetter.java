
package net.ender.cc.Scoring.sql;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import net.ender.cc.Scoring.Scoring;
import net.ender.cc.Scoring.util.Leaderboard;
import org.bukkit.entity.Player;

public class NonPlayerSQLGetter {
    private final Scoring plugin;

    public NonPlayerSQLGetter(final Scoring plugin) {
        this.plugin = plugin;
    }

    public void createTeamScore() {
        try {
            if (!this.teamExists()) {
                final PreparedStatement preparedStatement1 = this.plugin.sql.getConnection().prepareStatement("INSERT INTO `Team_Data` (`team_1`,`team_2`,`team_3`,`team_4`,`team_5`,`team_6`,`team_7`,`team_8`) VALUES (0, 0, 0, 0, 0, 0, 0, 0)");
                preparedStatement1.executeUpdate();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public boolean teamExists() {
        try (final PreparedStatement ps = this.plugin.sql.getConnection().prepareStatement("SELECT * FROM `Team_Data`;");
             final ResultSet rs = ps.executeQuery()) {
            return rs.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public void setTeamScore(final int d, final String team) {
        try {
            final PreparedStatement statement = this.plugin.sql.getConnection().prepareStatement("UPDATE `Team_Data` SET " + team + "=?");
            statement.setInt(1, d);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getTeamScore(final String team) {
        try (final PreparedStatement ps = this.plugin.sql.getConnection().prepareStatement("SELECT " + team + " FROM `Team_Data`");
             final ResultSet rs = ps.executeQuery()) {
            int coins = 0;
            if (rs.next()) {
                coins = rs.getInt(team);
                return coins;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getTeamName(final String team) {
        switch (team) {
            case "team_1": {
                return "Emerald Endermen";
            }
            case "team_2": {
                return "Indigo illiagers";
            }
            case "team_3": {
                return "Fuschia Phantoms";
            }
            case "team_4": {
                return "Bronze Blazes";
            }
            case "team_5": {
                return "Crimson Creepers";
            }
            case "team_6": {
                return "Charcoal Chickens";
            }
            case "team_7": {
                return "Violet Vexes";
            }
            case "team_8": {
                return "Cobalt Creepers";
            }
            case "none":
            default: {
                return "NONE";
            }
        }
    }
    public String getTeamFormatedName(final String team, Player player) {
        SQLGetter playerGetter = new SQLGetter(player, Scoring.getInstance());
        for (int i = 1; i <= 8; i++) {
            if(team.replace("team_", "").equals(String.valueOf(i))) {
                if (playerGetter.getTeamName().equalsIgnoreCase(team)) {
                    return "&l" + this.getTeamName("team_" + i);
                } else {
                    return "" + this.getTeamName("team_" + i);
                }
            }
        }
        return "NONE";
    }

    public String getLeaderboardTeamName(final String team) {
        switch (team) {
            case "team_1": {
                return "Emerald Endermen:";
            }
            case "team_2": {
                return "Indigo illiagers:";
            }
            case "team_3": {
                return "Fuschia Phantoms:";
            }
            case "team_4": {
                return "Bronze Blazes:";
            }
            case "team_5": {
                return "Crimson Creepers:";
            }
            case "team_6": {
                return "Charcoal Chickens:";
            }
            case "team_7": {
                return "Violet Vexes:";
            }
            case "team_8": {
                return "Cobalt Creepers:";
            }
            case "none":
            default: {
                return "NONE";
            }
        }
    }

    public String getTeamFromName(String teamName) {
        String team = teamName.toLowerCase();
        switch (team) {
            case "emerald endermen": {
                return "team_1";
            }
            case "indigo illiagers": {
                return "team_2";
            }
            case "fuschia phantoms": {
                return "team_3";
            }
            case "bronze blazes": {
                return "team_4";
            }
            case "crimson creepers": {
                return "team_5";
            }
            case "charcoal chickens": {
                return "team_6";
            }
            case "violet vexes": {
                return "team_7";
            }
            case "cobalt creepers": {
                return "team_8";
            }
            case "none":
            default: {
                return "NONE";
            }
        }
    }
}
