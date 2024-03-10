package frostpyro.frostapi.api.listeners.customEventListener;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.api.damageManager.DamagePacket;
import frostpyro.frostapi.api.damageManager.DamageType;
import frostpyro.frostapi.api.listeners.customEvents.attackEvents.AttackEvent;
import frostpyro.frostapi.api.listeners.customEvents.attackEvents.player.PlayerAttackEvent;
import frostpyro.frostapi.api.listeners.customEvents.projectileEvent.ProjectileEvent;
import frostpyro.frostapi.api.listeners.customEvents.projectileEvent.player.PlayerProjectileEvent;
import frostpyro.frostapi.dataManage.player.DataManage;
import frostpyro.frostapi.dataManage.player.YamlData;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

public class AttackEventListener implements Listener {
    public AttackEventListener(FrostAPI plugin){
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
    }
    DataManage manage = new YamlData();

    AttackEvent attackEvent;
    @EventHandler
    private void attack(EntityDamageEvent event){
        if(!(event instanceof EntityDamageByEntityEvent)) return;
        if(!(((EntityDamageByEntityEvent) event).getDamager() instanceof LivingEntity)) return;
        DamagePacket packet = new DamagePacket((LivingEntity) event.getEntity(), (LivingEntity) ((EntityDamageByEntityEvent) event).getDamager(), DamageType.DEFAULT);
        if((((EntityDamageByEntityEvent) event).getDamager() instanceof Player))
            attackEvent = new PlayerAttackEvent(event.getEntity(), packet, manage.getPlayerData((Player) ((EntityDamageByEntityEvent) event).getDamager()));
        else
            attackEvent = new AttackEvent(event.getEntity(), packet);
        Bukkit.getServer().getPluginManager().callEvent(attackEvent);
    }

    @EventHandler
    private void projectileAttack(ProjectileHitEvent event){
        if(event.getHitEntity() == null) return;
        ProjectileEvent projectileEvent;
        if(event.getEntity().getShooter() instanceof Player)
            projectileEvent = new PlayerProjectileEvent(event.getEntity(), event.getHitEntity(), manage.getPlayerData((Player) event.getEntity().getShooter()));
        else
            projectileEvent = new ProjectileEvent(event.getEntity(), event.getHitEntity());
        Bukkit.getPluginManager().callEvent(projectileEvent);
    }
}
