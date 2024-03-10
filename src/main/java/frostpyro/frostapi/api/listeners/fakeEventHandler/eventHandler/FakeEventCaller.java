package frostpyro.frostapi.api.listeners.fakeEventHandler.eventHandler;

import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface FakeEventCaller <E extends Event>{
    boolean isFake(@NotNull E event);
}
