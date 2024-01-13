package frostpyro.frostapi.skill.handler.total_handler;

import frostpyro.frostapi.skill.handler.entityAction.EntityHealthInteract;
import frostpyro.frostapi.skill.handler.entityAction.TargetEntity;
import frostpyro.frostapi.skill.handler.entityAction.damage_handler.DamageApply;
import frostpyro.frostapi.skill.handler.entityAction.heal_handler.HealApply;
import org.bukkit.entity.LivingEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EntityHealth {
    private LivingEntity livingEntity1;
    private LivingEntity livingEntity2;
    private double modifyHealth;
    public EntityHealth(LivingEntity livingEntity1, double modifyHealth, LivingEntity livingEntity2){
        this.livingEntity1 = livingEntity1;
        this.livingEntity2 = livingEntity2;
        this.modifyHealth = modifyHealth;
    }
    private final List<DamageApply> damageList = new ArrayList<>(Arrays.asList(new DamageApply(livingEntity1, modifyHealth), new DamageApply(livingEntity1, modifyHealth, livingEntity2)));
    private final List<HealApply> healList = new ArrayList<>(Arrays.asList(new HealApply(livingEntity1, modifyHealth)));

    private EntityHealthInteract<DamageApply> damageApply = new EntityHealthInteract<>();
    private EntityHealthInteract<HealApply> healApply = new EntityHealthInteract<>();

    public void act(EntityActionType type){
        switch(type) {
            case HEAL ->{
                healApply.setType(healList.get(0));
                healApply.activate();
            }
            case DAMAGE ->{
                damageApply.setType(damageList.get(0));
                damageApply.activate();
            }
            case DAMAGE_WITH_DAMAGE_GIVE -> {
                damageApply.setType(damageList.get(1));
                damageApply.activate();
            }
        }
    }
}
