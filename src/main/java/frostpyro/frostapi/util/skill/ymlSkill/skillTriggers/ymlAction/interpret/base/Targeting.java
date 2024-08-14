package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.interpret.base;

import frostpyro.frostapi.util.skill.trigger.TriggerData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import java.util.ArrayList;
import java.util.List;

public class Targeting {
    public TriggerData data;
    public Targeting(TriggerData data){
        this.data = data;
    }

    public Entity nearby(boolean isLiving){
        if(isLiving) return livingNearby();
        return nonLivingNearby();
    }

    public List<? extends Entity> range(boolean isLiving, double x, double y, double z){
        if(isLiving) return livingRange(x, y, z);
        return nonLivingRange(x, y, z);
    }

    private List<Entity> nonLivingRange(double x, double y, double z){
        return data.getSource().getWorld().getNearbyEntities(data.getSource(), x, y, z).stream().toList();
    }

    private List<LivingEntity> livingRange(double x, double y, double z){
        List<LivingEntity> livingEntities = new ArrayList<>();
        for(Entity entity : data.getSource().getWorld().getNearbyEntities(data.getSource(), x, y, z)){
            if(!(entity instanceof LivingEntity living)) continue;
            livingEntities.add(living);
        }

        return livingEntities;
    }

    private Entity nonLivingNearby(){
        Entity result = null;
        for(Entity entity : data.getCast().getEntity().getWorld().getEntities()){
            if(result == null) result = entity;
            if(result.getLocation().distance(data.getSource()) < entity.getLocation().distance(data.getSource())) result = entity;
        }
        return result;
    }

    private LivingEntity livingNearby(){
        LivingEntity result = null;
        for(Entity entity : data.getCast().getEntity().getWorld().getEntities()){
            if(!(entity instanceof LivingEntity lE)) continue;
            if(result == null) result = lE;
            if(result.getLocation().distance(data.getSource()) < lE.getLocation().distance(data.getSource())) result = lE;
        }
        return result;
    }

    public enum Target{

    }
}
