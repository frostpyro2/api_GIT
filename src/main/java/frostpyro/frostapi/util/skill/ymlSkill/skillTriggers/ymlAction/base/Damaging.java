package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.base;

import frostpyro.frostapi.util.skill.trigger.TriggerData;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import java.util.Collection;
import java.util.List;

public class Damaging {
    private TriggerData data;
    public Damaging(TriggerData data){
        this.data = data;
    }
    public void damage(double damage, CordinateBase range, Target target){
        switch (target){
            case SELF -> selfDamage(damage);
            case CLOSEST -> closestDamage(damage, range);
            case ALL -> allDamage(damage);
            case NEARBY -> nearbyDamage(damage, range);
            case TARGET -> targetDamage(damage);
        }
    }

    private void targetDamage(double damage){
        try{
            data.getData().getTarget().damage(damage);
        }
        catch (Exception e){
            //do nothing
        }
    }

    private void nearbyDamage(double damage, CordinateBase range){
        for(Entity entity : data.getSource().getWorld().getNearbyEntities(data.getSource(), range.getCordinate().get(0), range.getCordinate().get(1), range.getCordinate().get(2))){
            if(!(entity instanceof LivingEntity living)) continue;
            living.damage(damage);
        }
    }

    private void allDamage(double damage){
        for(Entity entity : data.getCast().getEntity().getWorld().getEntities()){
            if(!(entity instanceof LivingEntity living)) continue;
            living.damage(damage, data.getCast().getEntity());
        }
    }

    private void closestDamage(double damage, CordinateBase range){
        LivingEntity smallest = null;
        double minRange = Math.sqrt(range.getCordinate().get(0)*range.getCordinate().get(0) + range.getCordinate().get(1)*range.getCordinate().get(1) +range.getCordinate().get(2)*range.getCordinate().get(3));
        for(Entity entity : data.getSource().getWorld().getNearbyEntities(data.getSource(), range.getCordinate().get(0), range.getCordinate().get(1), range.getCordinate().get(2))){
            if(!(entity instanceof LivingEntity living)) continue;
            if(data.getSource().distance(living.getLocation()) < minRange){
                minRange = data.getSource().distance(living.getLocation());
                smallest = living;
            }
        }
        if(smallest == null) return;
        smallest.damage(damage, data.getCast().getEntity());
    }

    private void selfDamage(double damage){
        data.getCast().getEntity().damage(damage);
    }


    public enum Target{
        TARGET("@target"),
        SELF("@self"),
        CLOSEST("@closest"),
        NEARBY("@nearby"),
        ALL("@all")
        ;
        String target;
        Target(String target){
            this.target = target;
        }
    }
}
