package frostpyro.frostapi.api.listeners.fake;

import com.google.common.base.Function;
import org.bukkit.damage.DamageSource;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class FakeEntityDamagedByEntityEvent extends EntityDamageByEntityEvent {
    public FakeEntityDamagedByEntityEvent(@NotNull Entity damager, @NotNull Entity damagee, @NotNull EntityDamageEvent.DamageCause cause, @NotNull DamageSource damageSource, @NotNull Map<DamageModifier, Double> modifiers, @NotNull Map<DamageModifier, ? extends Function<? super Double, Double>> modifierFunctions) {
        super(damager, damagee, cause, damageSource, modifiers, modifierFunctions);
    }

    public FakeEntityDamagedByEntityEvent(@NotNull Entity damager, @NotNull Entity damagee, @NotNull EntityDamageEvent.DamageCause cause, @NotNull DamageSource damageSource, double damage) {
        super(damager, damagee, cause, damageSource, damage);
    }
}
