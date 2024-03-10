package frostpyro.frostapi.api.listeners.customEventListener;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.api.listeners.customEvents.projectileEvent.ProjectileEvent;
import frostpyro.frostapi.api.listeners.customEvents.projectileEvent.player.PlayerProjectileEvent;
import frostpyro.frostapi.dataManage.data.DataManage;
import frostpyro.frostapi.dataManage.stat.player.PlayerData;
import frostpyro.frostapi.dataManage.stat.player.YamlData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

public class AttackEventListener implements Listener {
    public AttackEventListener(FrostAPI plugin){
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
    }
    DataManage manage = new YamlData();
    @EventHandler
    private void attack(EntityDamageEvent event){

    }

    @EventHandler
    private void projectileAttack(ProjectileHitEvent event){
        if(event.getHitEntity() == null) return;
        ProjectileEvent projectileEvent;
        if(event.getEntity().getShooter() instanceof Player)
            projectileEvent = new PlayerProjectileEvent(event.getEntity(), event.getHitEntity(), (PlayerData) manage.getEntityData((Player) event.getEntity().getShooter()));
        else
            projectileEvent = new ProjectileEvent(event.getEntity(), event.getHitEntity());
        Bukkit.getPluginManager().callEvent(projectileEvent);
    }
}
