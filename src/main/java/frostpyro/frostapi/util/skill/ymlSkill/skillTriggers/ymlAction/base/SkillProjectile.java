package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.base;

import frostpyro.frostapi.util.skill.trigger.PlayerTriggerData;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Projectile;
import org.bukkit.util.Vector;

import java.util.Collection;
import java.util.List;

public class SkillProjectile {
    private Projectile projectileTmp;
    private PlayerTriggerData data;
    public SkillProjectile(Projectile projectileTmp, Vector velocity, PlayerTriggerData data){
        this.projectileTmp = projectileTmp;
        this.projectileTmp.setVelocity(velocity);
        this.data = data;
    }
    public Location getProjectileLocation(){
        return projectileTmp.getLocation();
    }
    public void setVelocity(double velocity){
        projectileTmp.setVelocity(projectileTmp.getVelocity().multiply(velocity));
    }

    public void setVelocity(Vector velocity){
        projectileTmp.setVelocity(velocity);
    }

    public void setPassenger(Entity entity){
        projectileTmp.addPassenger(entity);
    }

    public void deletePassenger(Entity entity){
        projectileTmp.removePassenger(entity);
    }
}
