package frostpyro.frostapi.api.listeners.customEvents.attackEvents;

import frostpyro.frostapi.api.damageManager.attackData.AttackData;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityEvent;
import org.jetbrains.annotations.NotNull;

public class AttackEvent extends EntityEvent implements Cancellable {
    private boolean canceled;
    private static HandlerList handler = new HandlerList();
    private final EntityDamageEvent event;
    private AttackData data;

    public AttackEvent(EntityDamageEvent event, AttackData data) {
        super(event.getEntity());
        this.event = event;
        this.data = data;
    }

    @Override
    public boolean isCancelled() {
        return canceled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.canceled = cancel;
    }

    public AttackData getAttack(){
        return data;
    }

    public EntityDamageEvent bukkit(){
        return event;
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
