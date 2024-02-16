package frostpyro.frostapi.api.listeners.customEvents.attackEvents;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageEvent;
import org.jetbrains.annotations.NotNull;

public class AttackEvent extends EntityDamageEvent implements Cancellable {
    private boolean canceled;
    private static HandlerList handler = new HandlerList();

    private LivingEntity damager;
    public AttackEvent(LivingEntity entity){
        super(entity, DamageCause.ENTITY_ATTACK, 0);
    }

    public AttackEvent(LivingEntity entity, LivingEntity damager) {
        super(entity, DamageCause.ENTITY_ATTACK, 0);
        this.damager = damager;
    }

    @Override
    public boolean isCancelled() {
        return canceled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.canceled = cancel;
    }

    public LivingEntity getDamager(){
        return this.damager;
    }
    @Override
    @NotNull
    public HandlerList getHandlers() {
        return handler;
    }

    @NotNull
    public static HandlerList getHandlerList(){
        return handler;
    }
}
