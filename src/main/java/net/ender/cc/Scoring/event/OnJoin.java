// 
// Decompiled by Procyon v0.5.36
// 

package net.ender.cc.Scoring.event;

import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import net.ender.cc.Scoring.sql.SQLGetter;
import net.ender.cc.Scoring.Scoring;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.Listener;

public class OnJoin implements Listener
{
    @EventHandler
    public void OnJoin(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        if (Scoring.getInstance().getConfig().getBoolean("mysql.use-mysql")) {
            final SQLGetter getter = new SQLGetter(player, Scoring.getInstance());
            getter.inject();
            System.out.println("Data sync for " + player.getName() + " complete!");
        }
        else {
            System.out.println("Please add a MySQL Database for the server to work");
        }
    }
}
