package frostpyro.frostapi.listeners.customEventListener;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.listeners.customEvents.AttackEvent;
import frostpyro.frostapi.listeners.customEvents.ExpChangeEvent;
import frostpyro.frostapi.players.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.world.ChunkLoadEvent;

public class CustomEventProvoke implements Listener {
    public CustomEventProvoke(FrostAPI plugin){
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler
    private void attack(EntityDamageByEntityEvent event){
        if(!(event.getDamager() instanceof Player player)) return;

        if(event.getCause() != EntityDamageEvent.DamageCause.ENTITY_ATTACK) return;
        if(event.getEntity().getLastDamageCause() != null){
            if(event.getEntity().getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.CUSTOM) return;
        }
        EntityDamageEvent newDamageEvent = new EntityDamageEvent(event.getEntity(), EntityDamageEvent.DamageCause.CUSTOM, event.getDamage());
        event.getEntity().setLastDamageCause(newDamageEvent);

        AttackEvent attackEvent = new AttackEvent(player);
        Bukkit.getPluginManager().callEvent(attackEvent);
    }

    @EventHandler
    private void kill(EntityDeathEvent event){
        if(event.getEntity().getKiller() == null) return;
        Player player = event.getEntity().getKiller();

        PlayerData playerData = new PlayerData(player.getUniqueId().toString(), player.getName(), 1,0,0,0);
        ExpChangeEvent expChangeEvent = new ExpChangeEvent(playerData);
        Bukkit.getPluginManager().callEvent(expChangeEvent);
    }
}
