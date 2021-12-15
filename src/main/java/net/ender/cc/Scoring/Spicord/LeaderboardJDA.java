package net.ender.cc.Scoring.Spicord;

import net.dv8tion.jda.api.Permission;
import net.ender.cc.Scoring.Scoring;
import net.ender.cc.Scoring.sql.NonPlayerSQLGetter;
import org.spicord.api.addon.SimpleAddon;
import org.spicord.bot.DiscordBot;
import org.spicord.bot.command.DiscordBotCommand;

import java.util.ArrayList;
import java.util.Arrays;

public class LeaderboardJDA extends SimpleAddon {
    private Scoring scoring;

    public LeaderboardJDA(Scoring scoring) {
        super("TeamTopScore", "Scoring::team_top", "Ender", "1.0.0", new String[] { "LeaderboardJDA", "LeaderboardJDA", "top", "Top" });
        this.scoring = scoring;
    }

    @Override
    public void onLoad(DiscordBot bot) {
        scoring.addBot(bot);
    }

    @Override
    public void onCommand(DiscordBotCommand command, String[] args) {
        NonPlayerSQLGetter teamGetter = new NonPlayerSQLGetter(scoring);

        if (command.getSender().hasPermission(Permission.MANAGE_CHANNEL)) {
            if(args.length == 1){
                if(args[0].equalsIgnoreCase("teams") || args[0].equalsIgnoreCase("team")) {
                    command.reply("======================LeaderboardJDA======================" + System.lineSeparator() + "1. " + teamGetter.getTeamName(net.ender.cc.Scoring.util.Leaderboard.entrySet().get(0).getKey()) + " With a Score of " + net.ender.cc.Scoring.util.Leaderboard.entrySet().get(0).getValue() + System.lineSeparator() + "2. " + teamGetter.getTeamName(net.ender.cc.Scoring.util.Leaderboard.entrySet().get(1).getKey()) + " With a Score of " + net.ender.cc.Scoring.util.Leaderboard.entrySet().get(1).getValue() + System.lineSeparator() + "3. " + teamGetter.getTeamName(net.ender.cc.Scoring.util.Leaderboard.entrySet().get(2).getKey()) + " With a Score of " + net.ender.cc.Scoring.util.Leaderboard.entrySet().get(2).getValue() + System.lineSeparator() + "4. " + teamGetter.getTeamName(net.ender.cc.Scoring.util.Leaderboard.entrySet().get(3).getKey()) + " With a Score of " + net.ender.cc.Scoring.util.Leaderboard.entrySet().get(3).getValue() + System.lineSeparator() + "5. " + teamGetter.getTeamName(net.ender.cc.Scoring.util.Leaderboard.entrySet().get(4).getKey()) + " With a Score of " + net.ender.cc.Scoring.util.Leaderboard.entrySet().get(4).getValue() + System.lineSeparator() + "6. " + teamGetter.getTeamName(net.ender.cc.Scoring.util.Leaderboard.entrySet().get(5).getKey()) + " With a Score of " + net.ender.cc.Scoring.util.Leaderboard.entrySet().get(5).getValue() + System.lineSeparator() + "7. " + teamGetter.getTeamName(net.ender.cc.Scoring.util.Leaderboard.entrySet().get(6).getKey()) + " With a Score of " + net.ender.cc.Scoring.util.Leaderboard.entrySet().get(6).getValue() + System.lineSeparator() + "8. " + teamGetter.getTeamName(net.ender.cc.Scoring.util.Leaderboard.entrySet().get(7).getKey()) + " With a Score of " + net.ender.cc.Scoring.util.Leaderboard.entrySet().get(7).getValue() + System.lineSeparator() + "======================LeaderboardJDA======================");
                } else if (args[0].equalsIgnoreCase("solo") || args[0].equalsIgnoreCase("solos")) {
                    ArrayList<String> list = new ArrayList<>();
                    for (int i = 0; i < net.ender.cc.Scoring.util.Leaderboard.entrySet2().size(); i++) {
                        list.add((i+1) + ". " + net.ender.cc.Scoring.util.Leaderboard.entrySet2().get(i).getKey() + " With a Score of " + net.ender.cc.Scoring.util.Leaderboard.entrySet2().get(i).getValue());
                    }
                    String listString = "";
                    listString += "======================LeaderboardJDA======================" + System.lineSeparator();
                    for (String s : list)
                    {
                        listString += s + System.lineSeparator();
                    }
                    listString += "======================LeaderboardJDA======================";

                    command.reply(listString);
                } else {
                    command.reply("Incorrect usage! -top/LeaderboardJDA [teams/solo] eg -top teams.");
                }
            }else{
                command.reply("Incorrect usage! -top/LeaderboardJDA [teams/solo] eg -top teams.");
            }
        } else {
            command.reply(command.getAuthorAsMention() + ", you do not have enough permission to run this command.");
        }
    }
}

