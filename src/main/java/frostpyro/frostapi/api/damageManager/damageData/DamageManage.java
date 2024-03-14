package frostpyro.frostapi.api.damageManager.damageData;

import frostpyro.frostapi.api.damageManager.attackData.AttackData;
import frostpyro.frostapi.dataManage.stat.StatProvider;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageManage {


    public AttackData findAttack(EntityDamageEvent event){
        if(event instanceof EntityDamageByEntityEvent){
            LivingEntity attacker = (LivingEntity) ((EntityDamageByEntityEvent)event).getDamager();
            StatProvider attack = StatProvider.get(attacker);
        }
        return null;
    }

    public DamageType[] types(EntityDamageEvent.DamageCause cause){
        switch(cause){
            case ENTITY_ATTACK, ENTITY_SWEEP_ATTACK, ENTITY_EXPLOSION, FALL, FALLING_BLOCK ->{
                return new DamageType[]{DamageType.PHYSICAL};
            }
            case DRAGON_BREATH, MAGIC, SONIC_BOOM ->{
                return new DamageType[]{DamageType.MAGIC};
            }
            case FIRE, FIRE_TICK->{
                return new DamageType[]{DamageType.PHYSICAL, DamageType.DOT};
            }
            case POISON -> {
                return new DamageType[]{DamageType.MAGIC, DamageType.DOT};
            }
            default -> {
                return new DamageType[0];
            }
        }
    }
}
