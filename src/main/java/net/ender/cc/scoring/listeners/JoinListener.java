// 
// Decompiled by Procyon v0.5.36
// 

package net.ender.cc.scoring.listeners;

import net.ender.cc.scoring.Scoring;
import net.ender.cc.scoring.sql.SQLPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (Scoring.getInstance().getConfig().getBoolean("mysql.use-mysql")) {
            final SQLPlayer getter = new SQLPlayer(player);
            getter.createPlayer();
            System.out.println("Data sync for " + player.getName() + " complete!");
        } else {
            System.out.println("Please add a MySQL Database for the server to work");
        }
    }

}
