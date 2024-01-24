package frostpyro.frostapi.listeners.customEventListener;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.listeners.customEvents.AttackEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.checkerframework.checker.units.qual.A;

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
        event.setCancelled(true);
    }
}
