package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.base;

import frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.Action;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Projectile;
import org.bukkit.util.Vector;

public class SkillProjectile {
    private Projectile projectileTmp;
    public SkillProjectile(Projectile projectileTmp, Vector velocity){
        this.projectileTmp = projectileTmp;
        this.projectileTmp.setVelocity(velocity);
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
