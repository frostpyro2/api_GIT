package frostpyro.frostapi.listeners.customEvents;

import frostpyro.frostapi.players.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageEvent;

public class AttackEvent extends Event {
    private static HandlerList handler = new HandlerList();

    private PlayerData playerData;

    private Player player;

    public AttackEvent(Player player) {
        super();
        this.player = player;
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
    public HandlerList getHandlers() {
        return handler;
    }

    public static HandlerList getHandlerList(){
        return handler;
    }
}
