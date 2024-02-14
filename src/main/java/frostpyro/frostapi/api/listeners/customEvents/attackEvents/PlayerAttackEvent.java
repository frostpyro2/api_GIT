package frostpyro.frostapi.api.listeners.customEvents.attackEvents;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class PlayerAttackEvent extends AttackEvent{
    public PlayerAttackEvent(LivingEntity entity, LivingEntity damager, Player player) {
        super(entity, damager, player);
    }
}
