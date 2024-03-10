package frostpyro.frostapi.api.listeners.customEvents.projectileEvent.player;

import frostpyro.frostapi.api.listeners.customEvents.projectileEvent.ProjectileEvent;
import frostpyro.frostapi.dataManage.stat.player.PlayerData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerProjectileEvent extends ProjectileEvent {
    private PlayerData playerData;
    private Player player;
    private static HandlerList handlerList = new HandlerList();
    public PlayerProjectileEvent(@NotNull Projectile projectile, @Nullable Entity hitEntity, PlayerData playerData) {
        super(projectile, hitEntity);
        this.playerData = playerData;
    }

    public PlayerProjectileEvent(@NotNull Projectile projectile, @Nullable Entity hitEntity, Player player) {
        super(projectile, hitEntity);
        this.player= player;
    }

    public Player getPlayer(){
        return player;
    }

    public PlayerData getPlayerData(){
        return playerData;
    }

    @Override
    public @NotNull HandlerList getHandlers(){
        return handlerList;
    }

    public static @NotNull HandlerList getHandlerList(){
        return handlerList;
    }
}
