package frostpyro.frostapi.api.listeners.customEvents.statUpgradeEvent;

import frostpyro.frostapi.dataManage.stat.StatProvider;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class StatUpgradeEvent extends Event{
    private static HandlerList handlerList = new HandlerList();
    private String stat;
    private StatProvider provider;

    public StatUpgradeEvent(String stat, StatProvider provider){
        this.stat = stat;
        this.provider = provider;
    }

    public String getStat() {
        return stat;
    }

    public StatProvider getProvider() {
        return provider;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList(){
        return handlerList;
    }
}
