package frostpyro.frostapi.handler.effect_handler.particle;

import frostpyro.frostapi.handler.effect_handler.TargetEffect;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;

public class ParticleApply extends TargetEffect<Particle> {
    private Entity target;
    private Location location;
    public ParticleApply(Particle particle, Entity target, Location location) {
        super(particle);
        this.target = target;
        this.location = location;
    }

    @Override
    public void action() {
        target.getWorld().spawnParticle(super.getObj(), location, 1);
    }
}
