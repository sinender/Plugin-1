// 
// Decompiled by Procyon v0.5.36
// 

package net.ender.cc.Scoring.sql;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import net.ender.cc.Scoring.Scoring;
import org.bukkit.entity.Player;

public class SQLGetter
{
    private final Player player;
    private final Scoring plugin;
    
    public SQLGetter(final Player player, final Scoring plugin) {
        this.player = player;
        this.plugin = plugin;
    }
    
    public void inject() {
        this.createPlayer();
    }
    
    public static void createTeamsTable() {
        final Scoring plugin = Scoring.getInstance();
        try {
            final PreparedStatement statement = plugin.sql.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS `Team_Data` (`team_1` INT(60),`team_2` INT(60),`team_3` INT(60),`team_4` INT(60),`team_5` INT(60),`team_6` INT(60),`team_7` INT(60),`team_8` INT(60))");
            statement.executeUpdate();
        }
        catch (SQLException error) {
            error.printStackTrace();
        }
    }
    
    public static void createTable() {
        final Scoring plugin = Scoring.getInstance();
        try {
            final PreparedStatement statement = plugin.sql.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS `Player_Data` (`UUID` varchar(50),`Player_Team` VARCHAR (50),`Player_Score` INT(60))");
            statement.executeUpdate();
        }
        catch (SQLException error) {
            error.printStackTrace();
        }
    }
    
    public void createPlayer() {
        try {
            if (!this.exists()) {
                final PreparedStatement preparedStatement1 = this.plugin.sql.getConnection().prepareStatement("INSERT INTO `Player_Data` (`UUID`,`Player_Team`,`Player_Score`) VALUES (?, ?, ?)");
                preparedStatement1.setString(1, this.player.getUniqueId().toString());
                preparedStatement1.setString(2, "NONE");
                preparedStatement1.setInt(3, 0);
                preparedStatement1.executeUpdate();
            }
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
    
    public boolean exists() {
        try (final PreparedStatement ps = this.plugin.sql.getConnection().prepareStatement("SELECT * FROM `Player_Data` WHERE `UUID`=?;")) {
            ps.setString(1, this.player.getUniqueId().toString());
            try (final ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public void setScore(final int d) {
        try {
            final PreparedStatement statement = this.plugin.sql.getConnection().prepareStatement("UPDATE `Player_Data` SET `Player_Score`=? WHERE `UUID`=?");
            statement.setInt(1, d);
            statement.setString(2, this.player.getUniqueId().toString());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public int getScore() {
        try (final PreparedStatement ps = this.plugin.sql.getConnection().prepareStatement("SELECT `Player_Score` FROM `Player_Data` WHERE `UUID`=?")) {
            ps.setString(1, this.player.getUniqueId().toString());
            try (final ResultSet rs = ps.executeQuery()) {
                int coins = 0;
                if (rs.next()) {
                    coins = rs.getInt("Player_Score");
                    return coins;
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public String getTeamName() {
        try (final PreparedStatement ps = this.plugin.sql.getConnection().prepareStatement("SELECT `Player_Team` FROM `Player_Data` WHERE `UUID`=?")) {
            ps.setString(1, this.player.getUniqueId().toString());
            try (final ResultSet rs = ps.executeQuery()) {
                String coins = "NONE";
                if (rs.next()) {
                    coins = rs.getString("Player_Team");
                    return coins;
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void setTeamName(final String d) {
        try {
            final PreparedStatement statement = this.plugin.sql.getConnection().prepareStatement("UPDATE `Player_Data` SET `Player_Team`=? WHERE `UUID`=?");
            statement.setString(1, d);
            statement.setString(2, this.player.getUniqueId().toString());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
