package frostpyro.frostapi.api.listeners.customEvents.attackEvents;

import frostpyro.frostapi.api.damageManager.DamagePacket;
import org.bukkit.damage.DamageSource;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityEvent;
import org.jetbrains.annotations.NotNull;

public class AttackEvent extends EntityEvent implements Cancellable {
    private boolean canceled;
    private static HandlerList handler = new HandlerList();

    private DamagePacket packet;

    public AttackEvent(@NotNull Entity what, DamagePacket packet) {
        super(what);
        this.packet = packet;
    }

    @Override
    public boolean isCancelled() {
        return canceled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.canceled = cancel;
    }

    public DamagePacket getPacket(){
        return this.packet;
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
