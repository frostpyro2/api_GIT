package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Projectile;
import org.bukkit.util.Vector;

public class SkillProjectile extends Action{
    private Projectile projectileTmp;
    public SkillProjectile(Projectile projectileTmp, Vector velocity){
        this.projectileTmp = projectileTmp;
        this.projectileTmp.setVelocity(velocity);
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
