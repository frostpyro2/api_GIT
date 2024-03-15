package frostpyro.frostapi.event;

import frostpyro.frostapi.FrostAPI;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.server.ServerLoadEvent;

public class DataListener implements Listener {
    public DataListener(FrostAPI api){
        Bukkit.getServer().getPluginManager().registerEvents(this, api);
    }

    @EventHandler
    private void join(PlayerJoinEvent event){

    }

    @EventHandler
    private void reload(ServerLoadEvent event){

    }
}
