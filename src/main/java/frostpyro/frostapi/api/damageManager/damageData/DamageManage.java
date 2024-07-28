package frostpyro.frostapi.api.damageManager.damageData;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.api.damageManager.attackData.AttackData;
import frostpyro.frostapi.dataManage.stat.StatProvider;
import frostpyro.frostapi.dataManage.stat.player.EquipSlot;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.persistence.PersistentDataType;

import java.lang.ref.PhantomReference;

public class DamageManage implements Listener {


    public AttackData findAttack(EntityDamageEvent event){
        Entity entity = event.getEntity();
        if(entity instanceof Player) return playerAttack(event);
        return nonPlayerAttack(event);
    }

    private AttackData playerAttack(EntityDamageEvent event){
        Entity entity = event.getEntity();
        if(event instanceof EntityDamageByEntityEvent){
            Entity damager = ((EntityDamageByEntityEvent) event).getDamager();
            if(damager instanceof LivingEntity){
                final StatProvider provider = StatProvider.get((LivingEntity)damager, EquipSlot.MAIN_HAND, true);
                return new AttackData(new DamageData(event.getDamage(), types(event.getCause())), (LivingEntity) entity, provider);
            }
            else if(damager instanceof Projectile){
                final LivingEntity shooter = (LivingEntity) ((Projectile)damager).getShooter();
                if(shooter == null) return new AttackData(new DamageData(event.getDamage()), (LivingEntity) entity, null);
                final StatProvider provider = StatProvider.get(shooter, EquipSlot.MAIN_HAND, false);
                return new AttackData(new DamageData(event.getDamage(), types(event.getCause())), (LivingEntity) entity, provider);
            }
        }
        return new AttackData(new DamageData(event.getDamage()), (LivingEntity) entity, null);
    }

    private AttackData nonPlayerAttack(EntityDamageEvent event){
        Entity entity = event.getEntity();
        if(event instanceof EntityDamageByEntityEvent actualEvent){
            Entity damager = actualEvent.getDamager();
            if(damager instanceof LivingEntity){

            }
            else if(damager instanceof Projectile){
                final LivingEntity shooter = (LivingEntity) ((Projectile)damager).getShooter();
                if(shooter == null) return new AttackData(new DamageData(event.getDamage()), (LivingEntity) entity, null);
                final StatProvider provider = StatProvider.getNonPlayer(damager.getPersistentDataContainer().get(FrostAPI.getPlugin().customEntity, PersistentDataType.STRING), frostpyro.frostapi.dataManage.stat.nonPlayer.EquipSlot.MAIN, true);
                if(provider == null)return new AttackData(new DamageData(event.getDamage()), (LivingEntity) entity, null);
                return new AttackData(new DamageData(event.getDamage(), types(event.getCause())), (LivingEntity) entity, provider);
            }
        }

        return new AttackData(new DamageData(event.getDamage()), (LivingEntity) entity, null);
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
