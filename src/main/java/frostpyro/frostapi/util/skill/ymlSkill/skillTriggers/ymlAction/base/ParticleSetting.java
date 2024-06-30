package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.base;

import org.bukkit.Location;
import org.bukkit.Particle;

public class ParticleSetting {
    Particle particle;
    Location location;
    int count;
    public ParticleSetting(Particle particle, Location location, int count){
        this.particle = particle;
        this.location = location;
        this.count = count;
    }

    public void spawn(){
        location.getWorld().spawnParticle(particle, location,count);
    }

    public enum TemplateParticle{
        CIRCLE,
        HELIX
    }

    public static class InitParticle extends ParticleSetting{
        private Particle particle;
        private TemplateParticle type;

        public InitParticle(Particle particle, Location location, int count,TemplateParticle type, double start, double end){
            super(particle, location, count);
        }

        @Override
        public void spawn(){
            switch (type){
                case HELIX -> helix();
                case CIRCLE -> circle();
            }
        }

        private void circle(){
            
        }

        private void helix(){

        }
    }
}
