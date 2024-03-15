package frostpyro.frostapi.api.damageManager.damageData;

import frostpyro.frostapi.api.damageManager.attackData.AttackData;
import frostpyro.frostapi.dataManage.stat.StatProvider;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageManage {


    public AttackData findAttack(EntityDamageEvent event){
        Entity damaged = event.getEntity();
        if(event instanceof EntityDamageByEntityEvent){
            Entity attacker = ((EntityDamageByEntityEvent)event).getDamager();
            if(attacker instanceof LivingEntity){
                StatProvider provider = StatProvider.get((LivingEntity) attacker);
                return new AttackData(new DamageData(event.getDamage(), types(event.getCause())), (LivingEntity) damaged, provider);
            }
            else if(attacker instanceof Projectile){
                if(((Projectile)attacker).getShooter() == null) return null;
                StatProvider provider = StatProvider.get((LivingEntity) ((Projectile)attacker).getShooter());
                return new AttackData(new DamageData(event.getDamage(), types(event.getCause())), (LivingEntity) damaged, provider);
            }
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
            case PROJECTILE -> {
                return new DamageType[]{DamageType.PHYSICAL, DamageType.PROJECTILE};
            }
            default -> {
                return new DamageType[0];
            }
        }
    }
}
