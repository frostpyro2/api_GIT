package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.interpret.base;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.util.Vector;

public class EntityLocation {
    private Entity entity;

    public EntityLocation(Entity entity){
        this.entity = entity;
    }

    public void teleport(Location location){
        entity.teleport(location);
    }

    public void setSpeed(Vector vector){
        entity.setVelocity(vector);
    }
}
