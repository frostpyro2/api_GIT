package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.interpret.base;


import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import java.util.List;

public class Damaging {
    private Entity target;
    public Damaging(Entity target){
        this.target = target;
    }

    public void damage(double amount, Entity damager, boolean selfDamage, boolean noDamageTick){
        try{
            if(noDamageTick){
                ((LivingEntity)target).setNoDamageTicks(0);
                damage(amount, damager, selfDamage, false);
            }
            else{
                ((LivingEntity)target).damage(amount, damager);
                ((LivingEntity)target).setNoDamageTicks(10);
            }
        }
        catch (Exception any){
            //do nothing
        }
    }
}
