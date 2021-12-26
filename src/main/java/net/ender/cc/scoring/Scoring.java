package net.ender.cc.scoring;

import lombok.Getter;
import net.ender.cc.scoring.commands.GeneralCompleter;
import net.ender.cc.scoring.commands.PlayerScoreCMD;
import net.ender.cc.scoring.commands.PlayerTeamCMD;
import net.ender.cc.scoring.commands.TeamScoreCMD;
import net.ender.cc.scoring.listeners.JoinListener;
import net.ender.cc.scoring.spicord.LeaderboardJDA;
import net.ender.cc.scoring.spicord.TeamScoreJDA;
import net.ender.cc.scoring.sql.SQLConnector;
import net.ender.cc.scoring.sql.SQLPlayer;
import net.ender.cc.scoring.sql.SQLTeam;
import net.ender.cc.scoring.util.PlaceholderExtension;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.spicord.Spicord;
import org.spicord.bot.DiscordBot;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class Scoring extends JavaPlugin {

    @Getter
    private static Scoring instance;
    @Getter
    private final Set<DiscordBot> bots = new HashSet<>();
    @Getter
    private SQLConnector sql;

    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        sql = new SQLConnector(this);

        try {
            sql.connect();
            sql.createPlayerDataTable();
            sql.createTeamDataTable();

            SQLTeam teamSQL = new SQLTeam();
            teamSQL.createTeamScore();

            System.out.println("SQL Successfully connected.");
            if (!Bukkit.getOnlinePlayers().isEmpty()) {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    SQLPlayer getter = new SQLPlayer(p);
                    getter.createPlayer();
                }
            }

        } catch (SQLException e) {
            System.out.println("SQL Connection failed.");
        }

        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            new PlaceholderExtension().register();
        }

        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getCommand("score").setExecutor(new PlayerScoreCMD());
        getCommand("team").setExecutor(new PlayerTeamCMD());
        getCommand("teamscore").setExecutor(new TeamScoreCMD());

        getCommand("score").setTabCompleter(new GeneralCompleter());
        getCommand("team").setTabCompleter(new GeneralCompleter());
        getCommand("teamscore").setTabCompleter(new GeneralCompleter());

        Spicord.getInstance().getAddonManager().registerAddon(new TeamScoreJDA(this));
        Spicord.getInstance().getAddonManager().registerAddon(new LeaderboardJDA(this));
    }

    public void addBot(DiscordBot bot) {
        bots.add(bot);
    }

}
