package frostpyro.frostapi.api.listeners.customEvents.projectileEvent.player;

import frostpyro.frostapi.api.listeners.customEvents.projectileEvent.ProjectileEvent;
import frostpyro.frostapi.dataManage.stat.player.PlayerDataTmp;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerProjectileEvent extends ProjectileEvent {
    private PlayerDataTmp playerDataTmp;
    private Player player;
    private static HandlerList handlerList = new HandlerList();
    public PlayerProjectileEvent(@NotNull Projectile projectile, @Nullable Entity hitEntity, PlayerDataTmp playerDataTmp) {
        super(projectile, hitEntity);
        this.playerDataTmp = playerDataTmp;
    }

    public PlayerProjectileEvent(@NotNull Projectile projectile, @Nullable Entity hitEntity, Player player) {
        super(projectile, hitEntity);
        this.player= player;
    }

    public Player getPlayer(){
        return player;
    }

    public PlayerDataTmp getPlayerData(){
        return playerDataTmp;
    }

    @Override
    public @NotNull HandlerList getHandlers(){
        return handlerList;
    }

    public static @NotNull HandlerList getHandlerList(){
        return handlerList;
    }
}
