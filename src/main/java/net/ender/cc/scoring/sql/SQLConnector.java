// 
// Decompiled by Procyon v0.5.36
// 

package net.ender.cc.scoring.sql;

import net.ender.cc.scoring.Scoring;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.sql.*;

public class SQLConnector {
    private final String host;
    private final String port;
    private final String database;
    private final String username;
    private final String password;
    private Scoring main;
    private Connection connection;

    public SQLConnector(final Scoring main) {
        this.main = Scoring.getInstance();
        this.host = this.main.getConfig().getString("mysql.host");
        this.port = this.main.getConfig().getString("mysql.port");
        this.database = this.main.getConfig().getString("mysql.database");
        this.username = this.main.getConfig().getString("mysql.username");
        this.password = this.main.getConfig().getString("mysql.password");
        this.main = main;
    }

    public boolean isConnected() {
        return this.connection != null;
    }

    public void connect() throws SQLException {
        if (!this.isConnected()) {
            this.connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database + "?useSSL=false", this.username, this.password);
        }
    }

    public void disconnect() {
        if (this.isConnected()) {
            try {
                this.connection.close();
            } catch (SQLException error) {
                error.printStackTrace();
            }
        }
    }

    public Connection getConnection() {
        return this.connection;
    }

    public ResultSet query(final String command) {
        if (command == null) {
            return null;
        }
        ResultSet rs = null;
        try {
            final Statement st = this.getConnection().createStatement();
            rs = st.executeQuery(command);
        } catch (Exception e) {
            final String message = e.getMessage();
            if (message != null) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "SQL Query:");
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Command: " + command);
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Error: " + message);
            }
        }
        return rs;
    }

    public void createTeamDataTable() {
        try {
            PreparedStatement statement = Scoring.getInstance()
                    .getSql()
                    .getConnection()
                    .prepareStatement("CREATE TABLE IF NOT EXISTS `Team_Data` (`team_1` INT(60),`team_2` INT(60),`team_3` " +
                            "INT(60),`team_4` INT(60),`team_5` INT(60),`team_6` INT(60),`team_7` INT(60),`team_8` INT(60))");
            //split for readability, it's a tad bit too long ^^

            statement.executeUpdate();
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public void createPlayerDataTable() {
        try {
            PreparedStatement statement = Scoring.getInstance()
                    .getSql()
                    .getConnection()
                    .prepareStatement("CREATE TABLE IF NOT EXISTS `Player_Data` (`UUID` varchar(50),`Player_Team` " +
                            "VARCHAR (50),`Player_Score` INT(60))");
            //split for readability, not required but keep for consistency :P &&
            statement.executeUpdate();
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }
}
