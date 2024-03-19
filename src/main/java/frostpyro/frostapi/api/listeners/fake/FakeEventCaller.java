package frostpyro.frostapi.api.listeners.fake;

import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

public interface FakeEventCaller<T extends Event> {
    boolean isFake(@NotNull T t);
}
