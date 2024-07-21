package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.base;

import frostpyro.frostapi.util.skill.trigger.TriggerData;
import org.bukkit.entity.Entity;

import java.util.Collection;
import java.util.List;

public class Targeting {
    private TriggerData data;
    private Entity trigger;
    public Targeting(TriggerData data){
        this.data = data;
        trigger = data.getCast().getEntity();
    }

    public Targeting(Entity trigger){
        this.trigger = trigger;
    }

    public Entity self(){
        try{
            return data.getCast().getEntity();
        }
        catch (Exception any){
            return trigger;
        }
    }

    public List<Entity> all(){
        try{
            return data.getSource().getWorld().getEntities();
        }
        catch (Exception any){
            return trigger.getWorld().getEntities();
        }
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

    public Entity closest(boolean same){
        Entity returned = trigger;
        List<Entity> entityCollection = trigger.getWorld().getEntities();
        if(same){
             for(int i = 0; i < entityCollection.size(); i++){
                 double smallest = trigger.getLocation().distance(entityCollection.get(i).getLocation());
                 for(int j = i; j < entityCollection.size(); j++){
                     if(smallest > entityCollection.get(j).getLocation().distance(trigger.getLocation())){
                         if(returned.getType() != trigger.getType()) continue;
                         smallest = entityCollection.get(j).getLocation().distance(trigger.getLocation());
                         returned = entityCollection.get(j);
                     }
                 }
                 return returned;
             }
             return returned;
        }

        double smallest = trigger.getLocation().distance(entityCollection.get(0).getLocation());
        for (Entity entity : entityCollection) {
            if (smallest > entity.getLocation().distance(trigger.getLocation())) {
                smallest = entity.getLocation().distance(trigger.getLocation());
                returned = entity;
            }
        }
        return returned;

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
