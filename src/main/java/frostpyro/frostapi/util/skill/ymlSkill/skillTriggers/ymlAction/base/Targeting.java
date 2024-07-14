package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.base;

import frostpyro.frostapi.util.skill.trigger.TriggerData;
import org.bukkit.entity.Entity;

import java.util.Collection;
import java.util.List;

public class Targeting {
    private TriggerData data;
    public Targeting(TriggerData data){
        this.data = data;
    }

    public Entity self(){
        return data.getCast().getEntity();
    }

    public List<Entity> all(){
        return data.getSource().getWorld().getEntities();
    }
    public Entity target(){
        try{
            return data.getData().getTarget();
        }
        catch (Exception any){
            return null;
        }
    }

    public Collection<Entity> nearby(CoordinateBase base, ThreeDimension dimension){
        return base.getCordinate().getWorld().getNearbyEntities(base.getCordinate(), dimension.getX(), dimension.getY(), dimension.getZ());
    }

    public Entity closest(CoordinateBase base, ThreeDimension dimension){
        double minDist = Math.sqrt(Math.pow(dimension.getX(), dimension.getX()) + Math.pow(dimension.getY(), dimension.getY()) + Math.pow(dimension.getZ(), dimension.getZ()));
        Entity closest = null;
        for(Entity entity : base.getCordinate().getWorld().getNearbyEntities(base.getCordinate(), dimension.getX(), dimension.getY(), dimension.getZ())){
            if(minDist >= entity.getLocation().distance(base.getCordinate())){
                minDist = entity.getLocation().distance(base.getCordinate());
                closest = entity;
            }
        }
        return closest;
    }

    public enum Target{
        target("@target"),
        self("@self"),
        nearby("@nearby"),
        all("@all"),
        closest("@closest");

        private String tar;
        Target(String target){
            this.tar = target;
        }

        public String getTargetStr(){
            return tar;
        }

        public static Target getTarget(String target){
            for(Target t : Target.values()){
                if(t.getTargetStr().equalsIgnoreCase(target)){
                    return t;
                }
            }
            throw new IllegalArgumentException("no context:"+target);
        }
    }
}
