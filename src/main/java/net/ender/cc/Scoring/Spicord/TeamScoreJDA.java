package net.ender.cc.Scoring.Spicord;

import net.dv8tion.jda.api.Permission;
import net.ender.cc.Scoring.Scoring;
import net.ender.cc.Scoring.sql.NonPlayerSQLGetter;
import net.ender.cc.Scoring.util.Leaderboard;
import org.spicord.api.addon.SimpleAddon;
import org.spicord.bot.DiscordBot;
import org.spicord.bot.command.DiscordBotCommand;

import java.util.ArrayList;
import java.util.Arrays;

public class TeamScoreJDA extends SimpleAddon {
    private Scoring scoring;

    public TeamScoreJDA(Scoring scoring) {
        super("TeamScore", "Scoring::team_score", "Ender", "1.0.0", new String[] { "teamscore", "Teamscore", " Teamscore", " teamscore" });
        this.scoring = scoring;
    }

    @Override
    public void onLoad(DiscordBot bot) {
        scoring.addBot(bot);
    }

    @Override
    public void onCommand(DiscordBotCommand command, String[] args) {
        ArrayList<String> teamNames = new ArrayList<>();
        ArrayList<String> teamTags = new ArrayList<>();

        for (String s : Arrays.asList("Emerald Endermen", "Indigo illiagers", "Fuschia Phantoms", "Bronze Blazes", "Crimson Creepers", "Charcoal Chickens", "Violet Vexes", "Cobalt Creepers")) {
            teamNames.add(s);
        }

        for (String s : Arrays.asList("team_1", "team_2", "team_3", "team_4", "team_5", "team_6", "team_7", "team_8")) {
            teamTags.add(s);
        }

        if (command.getSender().hasPermission(Permission.MANAGE_CHANNEL)) {
            if(args.length == 1) {
                NonPlayerSQLGetter teamGetter = new NonPlayerSQLGetter(Scoring.getInstance());
                for (String team : teamTags){
                    if (team.equalsIgnoreCase(args[0])) {
                        command.reply(teamGetter.getTeamName(args[0]) + " has a score of " + teamGetter.getTeamScore(args[0]) + ". They are placed " + Leaderboard.teamPos(args[0]));
                    }
                }
            }else if(args.length == 2){
                NonPlayerSQLGetter teamGetter = new NonPlayerSQLGetter(Scoring.getInstance());
                for (String team : teamNames) {
                    if (team.equalsIgnoreCase(args[0] + " " + args[1])) {
                        command.reply(args[0] + " " + args[1] + " has a score of " + teamGetter.getTeamScore(teamGetter.getTeamFromName(args[0] + " " + args[1])) + ". They are placed " + Leaderboard.teamPos(teamGetter.getTeamFromName(args[0] + " " + args[1])));
                    }
                }
            } else{
                if(command.getSender().getUser().getId().equalsIgnoreCase("355951205379211266")){
                    command.reply(command.getAuthorAsMention() + ", you litterally coded this LOL");
                } else {
                    command.reply(command.getAuthorAsMention() + ", the proper use of this command is -teamscore [teamTag]/[teamName] eg. -teamscore Emerald Endermen");

                }
            }
        } else {
            command.reply(command.getAuthorAsMention() + ", you do not have enough permission to run this command.");
        }
    }
}

