package frostpyro.frostapi.event;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.dataManage.player.DataManage;
import frostpyro.frostapi.dataManage.player.YamlData;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class DataListener implements Listener {
    public DataListener(FrostAPI api){
        Bukkit.getServer().getPluginManager().registerEvents(this, api);
    }

    @EventHandler
    private void join(PlayerJoinEvent event){
        DataManage manage = new YamlData();
        manage.createData(event.getPlayer());
    }
}
