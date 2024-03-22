package frostpyro.frostapi.event;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.dataManage.stat.data.PlayerFile;
import frostpyro.frostapi.dataManage.stat.player.PlayerDataTmp;
import frostpyro.frostapi.util.skill.skillItem.SkillItem;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerLoadEvent;

public class DataListener implements Listener {
    public DataListener(FrostAPI api){
        Bukkit.getServer().getPluginManager().registerEvents(this, api);
    }

    @EventHandler
    private void join(PlayerJoinEvent event){
        PlayerFile.generate(event.getPlayer());
        PlayerDataTmp.upload(event.getPlayer().getUniqueId(), new PlayerDataTmp(event.getPlayer().getUniqueId()));
    }

    @EventHandler
    private void left(PlayerQuitEvent event){
        PlayerDataTmp.flush(event.getPlayer().getUniqueId());
    }
}
