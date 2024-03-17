package frostpyro.frostapi.api.listeners.customEvents.attackEvents.player;

import frostpyro.frostapi.api.damageManager.attackData.AttackData;
import frostpyro.frostapi.api.listeners.customEvents.attackEvents.AttackEvent;
import frostpyro.frostapi.dataManage.stat.player.PlayerData;
import frostpyro.frostapi.dataManage.stat.player.PlayerDataTmp;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerAttackEvent extends AttackEvent {
    private static HandlerList handlerList = new HandlerList();
    private Player damager;
    private PlayerData damagerData;

    public PlayerAttackEvent(EntityDamageEvent event, AttackData data) {
        super(event, data);
        damagerData = (PlayerData) data.getAttacker();
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
