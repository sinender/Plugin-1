
package net.ender.cc.Scoring;

import net.ender.cc.Scoring.Commands.TeamScore;
import net.ender.cc.Scoring.Commands.PlayerTeamCMD;
import net.ender.cc.Scoring.Commands.TabCompleter;
import net.ender.cc.Scoring.Spicord.LeaderboardJDA;
import net.ender.cc.Scoring.Spicord.TeamScoreJDA;
import org.bukkit.command.CommandExecutor;
import net.ender.cc.Scoring.Commands.PlayerScore;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.event.Listener;
import net.ender.cc.Scoring.event.OnJoin;
import net.ender.cc.Scoring.util.PlaceHolderAPI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import net.ender.cc.Scoring.sql.NonPlayerSQLGetter;
import net.ender.cc.Scoring.sql.SQLGetter;
import net.ender.cc.Scoring.sql.MySQL;
import org.bukkit.plugin.java.JavaPlugin;
import org.spicord.Spicord;
import org.spicord.bot.DiscordBot;

public class Scoring extends JavaPlugin
{
    public static Scoring plugin;
    public MySQL sql;
    public Set<DiscordBot> bots;
    
    public void onEnable() {
        (Scoring.plugin = this).saveDefaultConfig();
        final NonPlayerSQLGetter nonPlayerSQL = new NonPlayerSQLGetter(getInstance());
        this.sql = new MySQL(this);
        try {
            this.sql.connect();
            SQLGetter.createTable();
            SQLGetter.createTeamsTable();
            nonPlayerSQL.createTeamScore();
            System.out.println("SQL Successfully connected.");
            if(!Bukkit.getOnlinePlayers().isEmpty()) {
                for (final Player p : Bukkit.getOnlinePlayers()) {
                    final SQLGetter getter = new SQLGetter(p, getInstance());
                    getter.inject();
                }
            }
        }
        catch (SQLException e) {
            System.out.println("SQL Connection failed.");
        }
        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            new PlaceHolderAPI().register();
        }
        this.getServer().getPluginManager().registerEvents((Listener)new OnJoin(), (Plugin)this);
        this.getCommand("score").setExecutor((CommandExecutor)new PlayerScore());
        this.getCommand("score").setTabCompleter((org.bukkit.command.TabCompleter)new TabCompleter());
        this.getCommand("team").setExecutor((CommandExecutor)new PlayerTeamCMD());
        this.getCommand("team").setTabCompleter((org.bukkit.command.TabCompleter)new TabCompleter());
        this.getCommand("teamscore").setExecutor((CommandExecutor)new TeamScore());
        this.getCommand("teamscore").setTabCompleter((org.bukkit.command.TabCompleter)new TabCompleter());


        Spicord.getInstance().getAddonManager().registerAddon(new TeamScoreJDA(this));
        Spicord.getInstance().getAddonManager().registerAddon(new LeaderboardJDA(this));
    }
    
    public void onDisable() {
    }
    
    public static Scoring getInstance() {
        return Scoring.plugin;
    }

    public void addBot(DiscordBot bot) {
        bots.add(bot);
    }

}
