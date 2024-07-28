package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.interpret.base;

import frostpyro.frostapi.dataManage.stat.StatProvider;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Projectile;

import java.util.Random;

public class ProjectileEntity {
    private Entity projectile;
    private StatProvider shooter;
    public ProjectileEntity(Entity projectile, StatProvider shooter){
        this.projectile = projectile;
        this.shooter = shooter;
        if(projectile instanceof Projectile projectile1){
            projectile1.setShooter(shooter.getEntity());
        }
    }

    public void move(double amount){
        Location location = projectile.getLocation();
        location.add(location.getDirection().normalize().multiply(amount/20));
        projectile.teleport(location);
    }

    public void power(double velocity){
        projectile.setVelocity(projectile.getLocation().getDirection().normalize().multiply(velocity));
    }
}
