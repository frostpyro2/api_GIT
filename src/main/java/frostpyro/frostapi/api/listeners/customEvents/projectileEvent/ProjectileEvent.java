package frostpyro.frostapi.api.listeners.customEvents.projectileEvent;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ProjectileEvent extends ProjectileHitEvent {
    private static HandlerList handlerList = new HandlerList();
    public ProjectileEvent(@NotNull Projectile projectile, @Nullable Entity hitEntity) {
        super(projectile, hitEntity);
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    @NotNull
    public static HandlerList getHandlerList(){
        return handlerList;
    }
}
