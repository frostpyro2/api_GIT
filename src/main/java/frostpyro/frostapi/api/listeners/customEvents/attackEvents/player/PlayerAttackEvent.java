package frostpyro.frostapi.api.listeners.customEvents.attackEvents.player;

import frostpyro.frostapi.api.listeners.customEvents.attackEvents.AttackEvent;
import frostpyro.frostapi.players.PlayerData;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import javax.annotation.concurrent.NotThreadSafe;

public class PlayerAttackEvent extends AttackEvent {
    private static HandlerList handlerList = new HandlerList();
    private Player damager;
    public PlayerAttackEvent(LivingEntity entity, Player damger) {
        super(entity);
        this.damager = damger;
    }

    public Player getPlayer(){
        return damager;
    }


    @Override
    @NotNull
    public HandlerList getHandlers(){
        return handlerList;
    }
    @NotNull
    public static HandlerList getHandlerList() {
        return handlerList;
    }
}
