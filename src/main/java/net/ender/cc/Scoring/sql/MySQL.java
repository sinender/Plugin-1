// 
// Decompiled by Procyon v0.5.36
// 

package net.ender.cc.Scoring.sql;

import java.sql.Statement;
import org.bukkit.ChatColor;
import org.bukkit.Bukkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import net.ender.cc.Scoring.Scoring;

public class MySQL
{
    private Scoring main;
    private final String host;
    private final String port;
    private final String database;
    private final String username;
    private final String password;
    private Connection connection;
    
    public MySQL(final Scoring main) {
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
            }
            catch (SQLException error) {
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
        }
        catch (Exception e) {
            final String message = e.getMessage();
            if (message != null) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "SQL Query:");
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Command: " + command);
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Error: " + message);
            }
        }
        return rs;
    }
}
