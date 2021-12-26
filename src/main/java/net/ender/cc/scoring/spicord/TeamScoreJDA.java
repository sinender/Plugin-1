package net.ender.cc.scoring.spicord;

import net.dv8tion.jda.api.Permission;
import net.ender.cc.scoring.Scoring;
import net.ender.cc.scoring.data.ScoringTeam;
import net.ender.cc.scoring.sql.SQLTeam;
import net.ender.cc.scoring.util.Leaderboard;
import org.spicord.api.addon.SimpleAddon;
import org.spicord.bot.DiscordBot;
import org.spicord.bot.command.DiscordBotCommand;

public class TeamScoreJDA extends SimpleAddon {
    private final Scoring scoring;

    public TeamScoreJDA(Scoring scoring) {
        super("TeamScore", "Scoring::team_score", "Ender", "1.0.0", new String[]{"teamscore", "Teamscore", " Teamscore", " teamscore"});
        this.scoring = scoring;
    }

    @Override
    public void onLoad(DiscordBot bot) {
        scoring.addBot(bot);
    }

    @Override
    public void onCommand(DiscordBotCommand command, String[] args) {
        if (command.getSender().hasPermission(Permission.MANAGE_CHANNEL)) {
            if (args.length == 1 || args.length == 2) {
                SQLTeam teamGetter = new SQLTeam();
                for (ScoringTeam team : ScoringTeam.values()) {
                    if (args.length == 1) {
                        if (team.name().equalsIgnoreCase(args[0])) {
                            command.reply(team.getFormattedName() + " has a score of " + teamGetter.getTeamScore(team) + ". They are placed " + Leaderboard.teamPos(team));
                        }
                    } else {
                        if (team.getFormattedName().equalsIgnoreCase(args[0] + " " + args[1])) {
                            command.reply(team.getFormattedName() + " has a score of " + teamGetter.getTeamScore(team) + ". They are placed " + Leaderboard.teamPos(team));
                        }
                    }
                }
            } else {
                if (command.getSender().getUser().getId().equalsIgnoreCase("355951205379211266")) {
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

