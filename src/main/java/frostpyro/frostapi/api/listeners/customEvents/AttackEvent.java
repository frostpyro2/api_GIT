package frostpyro.frostapi.api.listeners.customEvents;

import frostpyro.frostapi.players.PlayerData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageEvent;

import javax.annotation.Nonnull;

public class AttackEvent extends EntityDamageEvent implements Cancellable {
    private boolean canceled;
    private static HandlerList handler = new HandlerList();

    private PlayerData playerData;

    private Player player;

    private LivingEntity entity;

    public AttackEvent(LivingEntity entity, Player player) {
        super(entity, DamageCause.ENTITY_ATTACK, 0);
        this.player = player;
    }

    @Override
    public boolean isCancelled() {
        return canceled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.canceled = cancel;
    }

    public PlayerData getPlayerData(){
        return playerData;
    }

    public Player getPlayer(){
        return player;
    }

    public void setPlayerData(PlayerData playerData){
        this.playerData = playerData;
    }
    @Override
    @Nonnull
    public HandlerList getHandlers() {
        return handler;
    }

    public static HandlerList getHandlerList(){
        return handler;
    }
}
