package frostpyro.frostapi.util.lib;

import frostpyro.frostapi.FrostAPI;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

public class Utility {
    public static boolean isFake(@NotNull Event event){
        return FrostAPI.getPlugin().fakeEventManager().isFake(event);
    }
}
