package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.base;

import frostpyro.frostapi.util.skill.trigger.PlayerTriggerData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

public class Damaging {
    private PlayerTriggerData data;
    public Damaging(PlayerTriggerData data){
        this.data = data;
    }

    public void damage(LivingEntity entity, double amount, boolean noDamageTick, boolean selfDamage){
        try{
            if(noDamageTick){
                entity.setNoDamageTicks(1);
                damage(entity, amount, false, selfDamage);
            }
            if(selfDamage && entity == data.getCast().getEntity()){
                entity.setNoDamageTicks(10);
                return;
            }

            entity.damage(amount, data.getCast().getEntity());
            entity.setNoDamageTicks(10);
        }
        catch (Exception e){
            //do nothing
        }
    }
}
