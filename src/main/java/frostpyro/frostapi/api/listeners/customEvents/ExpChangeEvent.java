package frostpyro.frostapi.api.listeners.customEvents;

import frostpyro.frostapi.dataManage.stat.player.PlayerDataTmp;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import javax.annotation.Nonnull;
//detects data's change
public class ExpChangeEvent extends Event {
    private static HandlerList handlerList = new HandlerList();
    private PlayerDataTmp playerDataTmp;
    public ExpChangeEvent(PlayerDataTmp playerDataTmp) {
        super();
        this.playerDataTmp = playerDataTmp;
    }

    public ExpChangeEvent(boolean isAsync) {
        super(isAsync);
    }

    public PlayerDataTmp getPlayerData(){
        return playerDataTmp;
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
