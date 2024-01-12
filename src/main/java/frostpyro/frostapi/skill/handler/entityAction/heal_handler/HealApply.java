package frostpyro.frostapi.skill.handler.entityAction.heal_handler;

import frostpyro.frostapi.skill.handler.entityAction.TargetEntity;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;

import javax.swing.text.html.parser.Entity;

public class HealApply extends TargetEntity {
    private double healAmount;
    private LivingEntity healGive;

    public HealApply(LivingEntity livingEntity, double healAmount) {
        super(livingEntity);
        this.healAmount = healAmount;
    }

    @Override
    public void actFunction() {
        if(super.getLivingEntity().getHealth() >= super.getLivingEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()) return;
        super.getLivingEntity().setHealth(super.getLivingEntity().getHealth() + healAmount);
    }
}
