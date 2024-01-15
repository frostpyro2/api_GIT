package frostpyro.frostapi.listeners.customEvents;

import frostpyro.frostapi.players.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageEvent;

import javax.annotation.Nonnull;

public class AttackEvent extends Event implements Cancellable {
    private boolean canceled;
    private static HandlerList handler = new HandlerList();

    private PlayerData playerData;

    private Player player;

    public AttackEvent(Player player) {
        super();
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

    public AttackEvent(boolean isAsync) {
        super(isAsync);
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
