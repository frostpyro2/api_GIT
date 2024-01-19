package frostpyro.frostapi.handler.entityAction.heal_handler;

import frostpyro.frostapi.handler.entityAction.TargetEntity;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;

import java.util.Objects;

public class HealApply extends TargetEntity {
    private double healAmount;
    private LivingEntity healGive;

    public HealApply(LivingEntity livingEntity, double healAmount) {
        super(livingEntity);
        this.healAmount = healAmount;
    }

    @Override
    public void actFunction() {
        if(!isNull()) return;
        if(super.getLivingEntity().getHealth() >= Objects.requireNonNull(super.getLivingEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue()) return;
        super.getLivingEntity().setHealth(super.getLivingEntity().getHealth() + healAmount);
    }

    private boolean isNull(){
        if(super.getLivingEntity() == null) return false;
        return super.getLivingEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH) != null;
    }
}
