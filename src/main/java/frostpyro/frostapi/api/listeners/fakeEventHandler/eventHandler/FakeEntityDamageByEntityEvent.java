package frostpyro.frostapi.api.listeners.fakeEventHandler.eventHandler;

import com.google.common.base.Function;
import org.bukkit.damage.DamageSource;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public abstract class FakeEntityDamageByEntityEvent extends EntityDamageByEntityEvent {
    public FakeEntityDamageByEntityEvent(@NotNull Entity damager, @NotNull Entity damagee, @NotNull EntityDamageEvent.DamageCause cause, @NotNull DamageSource damageSource, double damage) {
        super(damager, damagee, cause, damageSource, damage);
    }

    public FakeEntityDamageByEntityEvent(@NotNull Entity damager, @NotNull Entity damagee, @NotNull EntityDamageEvent.DamageCause cause, @NotNull DamageSource damageSource, @NotNull Map<DamageModifier, Double> modifiers, @NotNull Map<DamageModifier, ? extends Function<? super Double, Double>> modifierFunctions) {
        super(damager, damagee, cause, damageSource, modifiers, modifierFunctions);
    }
}
