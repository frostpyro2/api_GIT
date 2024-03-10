package frostpyro.frostapi.api.listeners.customEvents.attackEvents.player;

import frostpyro.frostapi.api.damageManager.DamagePacket;
import frostpyro.frostapi.api.listeners.customEvents.attackEvents.AttackEvent;
import frostpyro.frostapi.dataManage.player.PlayerData;
import org.bukkit.damage.DamageSource;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PlayerAttackEvent extends AttackEvent {
    private static HandlerList handlerList = new HandlerList();
    private Player damager;
    private PlayerData damagerData;

    public PlayerAttackEvent(@NotNull Entity what, DamagePacket packet, PlayerData playerData) {
        super(what, packet);
        this.damagerData = playerData;
    }

    @Deprecated(forRemoval = true)
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
