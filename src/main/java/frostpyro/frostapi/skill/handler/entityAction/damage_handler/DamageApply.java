package frostpyro.frostapi.skill.handler.entityAction.damage_handler;

import frostpyro.frostapi.skill.handler.entityAction.TargetEntity;
import org.bukkit.entity.LivingEntity;

public class DamageApply extends TargetEntity {

    private double damage;
    private LivingEntity damageGive;

    public DamageApply(LivingEntity livingEntity, double damage){
        super(livingEntity);
        this.damage = damage;
    }

    public DamageApply(LivingEntity livingEntity, double damage, LivingEntity damageGive) {
        super(livingEntity);
        this.damage = damage;
        this.damageGive = damageGive;
    }

    @Override
    public void actFunction() {
        if(damageGive == null) {
            super.getLivingEntity().damage(damage);
            return;
        }
        super.getLivingEntity().damage(damage, damageGive);
    }


}
