package frostpyro.frostapi.api.listeners.customEvents;

import frostpyro.frostapi.players.PlayerData;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import javax.annotation.Nonnull;
//detects data's change
public class ExpChangeEvent extends Event {
    private static HandlerList handlerList = new HandlerList();
    private PlayerData playerData;
    public ExpChangeEvent(PlayerData playerData) {
        super();
        this.playerData = playerData;
    }

    public ExpChangeEvent(boolean isAsync) {
        super(isAsync);
    }

    public PlayerData getPlayerData(){
        return playerData;
    }

    @Override
    @Nonnull
    public String getEventName() {
        return super.getEventName();
    }

    @Override
    @Nonnull
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList(){
        return handlerList;
    }
}
