package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.interpret.base;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;

public class EntitySetting {
    private Entity entity;
    public EntitySetting(Entity entity){
        this.entity = entity;
    }

    public void marker(boolean isMarker){
        if(!(entity instanceof ArmorStand stand)) return;
        stand.setMarker(isMarker);
    }

    public void small(boolean isSmall){
        if(!(entity instanceof ArmorStand stand)) return;
        stand.setSmall(isSmall);
    }

    public void invisible(boolean isInvisible){
        if(!(entity instanceof ArmorStand stand)) return;
        stand.setInvisible(isInvisible);
    }

    public void gravity(boolean isGravity){
        entity.setGravity(isGravity);
    }
}
