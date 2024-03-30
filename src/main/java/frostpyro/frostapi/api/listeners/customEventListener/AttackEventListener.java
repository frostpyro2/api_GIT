package frostpyro.frostapi.api.listeners.customEventListener;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.api.damageManager.attackData.AttackData;
import frostpyro.frostapi.api.damageManager.damageData.DamageManage;
import frostpyro.frostapi.api.listeners.customEvents.attackEvents.AttackEvent;
import frostpyro.frostapi.api.listeners.customEvents.attackEvents.player.PlayerAttackEvent;
import frostpyro.frostapi.dataManage.stat.player.PlayerData;
import frostpyro.frostapi.util.lib.Utility;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

import java.util.HashMap;
import java.util.Map;

public class AttackEventListener implements Listener {
    public AttackEventListener(FrostAPI plugin){
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    private void attack(EntityDamageEvent event){
        if(Utility.isFake(event)||!(event.getEntity() instanceof LivingEntity)) return;

        AttackData data = FrostAPI.getPlugin().damage().findAttack(event);
        if(data.isPlayer() && ((Player)data.getAttacker().getEntity()).getGameMode() == GameMode.SPECTATOR) return;
        final AttackEvent attackEvent = data.isPlayer() ? new PlayerAttackEvent(event, data) : new AttackEvent(event, data);
        Bukkit.getPluginManager().callEvent(attackEvent);
        if(attackEvent.isCancelled())
            return;
        event.setDamage(data.getDamage().getDamage());
    }
}
