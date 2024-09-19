package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.interpret.base;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import java.util.List;

public class Condition {
    public boolean isDeadRadius(List<? extends org.bukkit.entity.Entity> entities){
        for(Entity entity : entities){
            if(!(entity instanceof LivingEntity living)) continue;
            if(!living.isDead()) return false;
        }
        return true;
    }
}
