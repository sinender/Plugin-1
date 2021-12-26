// 
// Decompiled by Procyon v0.5.36
// 

package net.ender.cc.scoring.sql;

import lombok.RequiredArgsConstructor;
import net.ender.cc.scoring.Scoring;
import net.ender.cc.scoring.data.ScoringTeam;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//TODO: Include some sort of caching of player data as to not fetch their data from SQL everytime
@RequiredArgsConstructor
public class SQLPlayer {
    private final Player player;

    public void createPlayer() {
        if (exists())
            return;

        try (PreparedStatement statement = Scoring.getInstance()
                .getSql()
                .getConnection()
                .prepareStatement("INSERT INTO `Player_Data` (`UUID`,`Player_Team`,`Player_Score`) VALUES (?, ?, ?)")) {

            statement.setString(1, this.player.getUniqueId().toString());
            statement.setString(2, "NONE");
            statement.setInt(3, 0);
            statement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public boolean exists() {
        try (PreparedStatement statement = Scoring.getInstance()
                .getSql()
                .getConnection()
                .prepareStatement("SELECT * FROM `Player_Data` WHERE `UUID`=?;")) {

            statement.setString(1, this.player.getUniqueId().toString());

            try (ResultSet rs = statement.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public int getScore() {
        try (PreparedStatement statement = Scoring.getInstance()
                .getSql()
                .getConnection()
                .prepareStatement("SELECT `Player_Score` FROM `Player_Data` WHERE `UUID`=?")) {

            statement.setString(1, this.player.getUniqueId().toString());
            try (ResultSet rs = statement.executeQuery()) {
                int coins = 0;
                if (rs.next()) {
                    coins = rs.getInt("Player_Score");
                    return coins;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void setScore(final int d) {
        try (PreparedStatement statement = Scoring.getInstance()
                .getSql()
                .getConnection()
                .prepareStatement("UPDATE `Player_Data` SET `Player_Score`=? WHERE `UUID`=?")) {

            statement.setInt(1, d);
            statement.setString(2, this.player.getUniqueId().toString());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ScoringTeam getTeam() {
        try (PreparedStatement ps = Scoring.getInstance()
                .getSql()
                .getConnection()
                .prepareStatement("SELECT `Player_Team` FROM `Player_Data` WHERE `UUID`=?")) {

            ps.setString(1, this.player.getUniqueId().toString());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return ScoringTeam.valueOf(rs.getString("Player_Team").toUpperCase());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void setTeamName(final String d) {
        try {
            final PreparedStatement statement = Scoring.getInstance()
                    .getSql()
                    .getConnection()
                    .prepareStatement("UPDATE `Player_Data` SET `Player_Team`=? WHERE `UUID`=?");

            statement.setString(1, d);
            statement.setString(2, this.player.getUniqueId().toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
