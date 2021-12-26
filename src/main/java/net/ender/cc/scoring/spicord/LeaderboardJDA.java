package net.ender.cc.scoring.spicord;

import net.dv8tion.jda.api.Permission;
import net.ender.cc.scoring.Scoring;
import net.ender.cc.scoring.data.ScoringTeam;
import net.ender.cc.scoring.util.Leaderboard;
import org.spicord.api.addon.SimpleAddon;
import org.spicord.bot.DiscordBot;
import org.spicord.bot.command.DiscordBotCommand;

import java.util.ArrayList;
import java.util.Map;

public class LeaderboardJDA extends SimpleAddon {
    private final Scoring scoring;

    public LeaderboardJDA(Scoring scoring) {
        super("TeamTopScore", "Scoring::team_top", "Ender", "1.0.0", new String[]{"LeaderboardJDA", "LeaderboardJDA", "top", "Top"});
        this.scoring = scoring;
    }

    @Override
    public void onLoad(DiscordBot bot) {
        scoring.addBot(bot);
    }

    @Override
    public void onCommand(DiscordBotCommand command, String[] args) {
        if (command.getSender().hasPermission(Permission.MANAGE_CHANNEL)) {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("teams") || args[0].equalsIgnoreCase("team")) {


                    command.reply("======================LeaderboardJDA======================"
                            + getTeamsMessage(Leaderboard.entrySet())
                            + "======================LeaderboardJDA======================");
                } else if (args[0].equalsIgnoreCase("solo") || args[0].equalsIgnoreCase("solos")) {
                    command.reply("======================LeaderboardJDA======================"
                            + getTeamsMessage(Leaderboard.entrySet2())
                            + "======================LeaderboardJDA======================");
                } else {
                    command.reply("Incorrect usage! -top/LeaderboardJDA [teams/solo] eg -top teams.");
                }
            } else {
                command.reply("Incorrect usage! -top/LeaderboardJDA [teams/solo] eg -top teams.");
            }
        } else {
            command.reply(command.getAuthorAsMention() + ", you do not have enough permission to run this command.");
        }
    }

    private <T> String getTeamsMessage(ArrayList<Map.Entry<T, Integer>> scores) {
        StringBuilder teamsMessage = new StringBuilder();
        for (ScoringTeam team : ScoringTeam.values()) {
            teamsMessage.append(System.lineSeparator())
                    .append(team.ordinal() + 1)
                    .append(". ")
                    .append(team.getFormattedName())
                    .append(" With a Score of ")
                    .append(scores.get(team.ordinal()).getValue());
        }

        teamsMessage.append(System.lineSeparator());

        return teamsMessage.toString();
    }
}

