package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.base;

import frostpyro.frostapi.util.skill.trigger.TriggerData;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import java.util.Collection;

public class Damaging {
    private double damage;

    private Location damageLoc;

    private double[] distance;
    private TriggerData data;

    public Damaging(double damage, Location damageLoc, double[] distance, TriggerData data){
        this.damage = damage;
        this.damageLoc = damageLoc;
        this.distance = distance;
        this.data = data;
    }

    public void damage(){
        Collection<Entity> entities = damageLoc.getWorld().getNearbyEntities(damageLoc, distance[0], distance[1], distance[2]);
        entityDamage(entities);
    }

    private void entityDamage(Collection<Entity> entities){
        for(Entity entity : entities){
            if(!(entity instanceof LivingEntity living)) continue;
            data.getCast().addDamage(living);
            living.damage(damage, data.getCast().getEntity());
        }
    }

    public void setDamageLoc(Location damageLoc){
        this.damageLoc = damageLoc;
    }
}
