package net.ender.cc.scoring.sql;

import net.ender.cc.scoring.Scoring;
import net.ender.cc.scoring.data.ScoringTeam;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//TODO: move into enum
public class SQLTeam {

    public void createTeamScore() {
        if (teamsExist())
            return;

        try {
            PreparedStatement statement = Scoring.getInstance()
                    .getSql()
                    .getConnection()
                    .prepareStatement("INSERT INTO `Team_Data` (?) VALUES (?)");

            StringBuilder teams = new StringBuilder();
            StringBuilder values = new StringBuilder();
            for (ScoringTeam team : ScoringTeam.values()) {
                teams.append("`")
                        .append(team.name())
                        .append("`")
                        .append(",");

                values.append("0,");
            }

            statement.setString(1, teams.delete(teams.length() - 1, teams.length()).toString());
            statement.setString(2, values.delete(values.length() - 1, values.length()).toString());

            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public boolean teamsExist() {
        try (PreparedStatement statement = Scoring.getInstance()
                .getSql()
                .getConnection()
                .prepareStatement("SELECT * FROM `Team_Data`;");

             ResultSet rs = statement.executeQuery()) {
            return rs.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public void setTeamScore(int d, ScoringTeam team) {
        try {
            PreparedStatement statement = Scoring.getInstance()
                    .getSql()
                    .getConnection()
                    .prepareStatement("UPDATE `Team_Data` SET ? = ?");
            statement.setString(1, team.name());
            statement.setInt(2, d);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getTeamScore(ScoringTeam team) {
        try (PreparedStatement statement = Scoring.getInstance()
                .getSql()
                .getConnection()
                .prepareStatement("SELECT ? FROM `Team_Data`")) {

            statement.setString(1, team.name());

            try (ResultSet rs = statement.executeQuery()) {
                int coins;

                if (rs.next()) {
                    coins = rs.getInt(team.name());
                    return coins;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getLeaderboardTeamName(ScoringTeam team) {
        return (team != null) ? team.getFormattedName() + ":" : "NONE";
    }

    public ScoringTeam getTeamFromName(String teamName) {
        return ScoringTeam.fromName(teamName);
    }
}
