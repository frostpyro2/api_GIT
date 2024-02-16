package frostpyro.frostapi.api.listeners.customEventListener;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.api.listeners.customEvents.attackEvents.AttackEvent;
import frostpyro.frostapi.api.listeners.customEvents.attackEvents.player.PlayerAttackEvent;
import frostpyro.frostapi.api.listeners.customEvents.projectileEvent.ProjectileEvent;
import frostpyro.frostapi.api.listeners.customEvents.projectileEvent.player.PlayerProjectileEvent;
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
    @EventHandler
    private void attack(EntityDamageEvent event){
        if(!(event instanceof EntityDamageByEntityEvent)) return;
        AttackEvent attackEvent;
        if(!(((EntityDamageByEntityEvent) event).getDamager() instanceof LivingEntity)) return;
        if(((EntityDamageByEntityEvent) event).getDamager() instanceof Player)
            attackEvent = new PlayerAttackEvent((LivingEntity) event.getEntity(), (Player) ((EntityDamageByEntityEvent) event).getDamager());
        else
            attackEvent = new AttackEvent((LivingEntity) event.getEntity(), (LivingEntity) ((EntityDamageByEntityEvent) event).getDamager());
        Bukkit.getPluginManager().callEvent(attackEvent);
    }

    @EventHandler
    private void projectileAttack(ProjectileHitEvent event){
        if(event.getHitEntity() == null) return;
        ProjectileEvent projectileEvent;
        if(event.getEntity().getShooter() instanceof Player)
            projectileEvent = new PlayerProjectileEvent(event.getEntity(), event.getHitEntity(), (Player) event.getEntity().getShooter());
        else
            projectileEvent = new ProjectileEvent(event.getEntity(), event.getHitEntity());
        Bukkit.getPluginManager().callEvent(projectileEvent);
    }
}
