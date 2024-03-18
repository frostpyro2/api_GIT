package frostpyro.frostapi.api.listeners.customEventListener;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.api.damageManager.attackData.AttackData;
import frostpyro.frostapi.api.damageManager.damageData.DamageManage;
import frostpyro.frostapi.api.listeners.customEvents.attackEvents.AttackEvent;
import frostpyro.frostapi.api.listeners.customEvents.attackEvents.player.PlayerAttackEvent;
import frostpyro.frostapi.dataManage.stat.player.PlayerData;
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
    @EventHandler
    private void attack(EntityDamageEvent event){
        AttackData data = FrostAPI.getPlugin().damage().findAttack(event);
        if(data == null) return;
        final AttackEvent attackEvent = data.isPlayer() ? new PlayerAttackEvent(event, data) : new AttackEvent(event, data);
        Bukkit.getPluginManager().callEvent(attackEvent);
    }
}
