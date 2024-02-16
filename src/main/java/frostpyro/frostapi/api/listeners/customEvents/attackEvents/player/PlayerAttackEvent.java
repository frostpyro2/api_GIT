package frostpyro.frostapi.api.listeners.customEvents.attackEvents.player;

import frostpyro.frostapi.api.listeners.customEvents.attackEvents.AttackEvent;
import frostpyro.frostapi.dataManage.player.PlayerData;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PlayerAttackEvent extends AttackEvent {
    private static HandlerList handlerList = new HandlerList();
    private Player damager;
    private PlayerData damagerData;
    public PlayerAttackEvent(LivingEntity entity, Player damger) {
        super(entity);
        this.damager = damger;
    }

    public PlayerAttackEvent(LivingEntity entity, PlayerData damager){
        super(entity);
        damagerData = damager;
    }

    public Player getPlayer(){
        return damager;
    }

    public PlayerData getPlayerData(){
        return damagerData;
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
