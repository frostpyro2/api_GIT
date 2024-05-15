package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.event.SkillTriggerListener;
import frostpyro.frostapi.util.skill.trigger.TriggerData;
import frostpyro.frostapi.util.skill.ymlSkill.yamlInterpret.RadiusInterpret;
import jdk.jfr.Frequency;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.configuration.Configuration;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;

public class SkillEffect implements Action{
    private TriggerData data;
    private Configuration configuration;
    public SkillEffect(Configuration configuration, TriggerData data){
        this.data = data;
        this.configuration = configuration;
    }

    public void section(){
        WeakReference<Effect> skillEffect = new WeakReference<>(new Effect(data, configuration.getList("skill.effect")));
        if(skillEffect.get() == null) return;
        skillEffect.get().runTask(FrostAPI.getPlugin());
    }

    private static class Effect extends BukkitRunnable{
        private int index = 0;
        private int delay = 0;
        private TriggerData data;
        private List<?> act;
        Effect(TriggerData data, List<?> act){
            this.data = data;
            this.act = act;
        }
        @Override
        public void run() {
            if(act == null || index >= act.size()){
                this.cancel();
                return;
            }

            for(Object effect : act){
                if(effect instanceof Map<?,?> effectMap){
                    if(effectMap.containsKey("effect")){
                        new BukkitRunnable(){
                            @Override
                            public void run() {
                                Map<?, ?> particleMap = (Map<?, ?>) effectMap.get("effect");
                                effect(particleMap);
                            }
                        }.runTaskLater(FrostAPI.getPlugin(), delay);
                    }
                    else if(effectMap.containsKey("definedEffect")){
                        new BukkitRunnable(){
                            @Override
                            public void run() {
                                Map<?, ?> particleMap = (Map<?, ?>) effectMap.get("definedEffect");
                                definedEffect(particleMap);
                            }
                        }.runTaskLater(FrostAPI.getPlugin(), delay);
                    }
                    else if(effectMap.containsKey("delay")){
                        delay = (int) effectMap.get("delay");
                    }
                }
            }
        }

        private void effect(Map<?, ?> effectSummon){
            int count;
            try{
                count = (int)effectSummon.get("count");
            }
            catch (Exception any){
                count = 1;
            }

            Particle particle;

            try{
                particle = Particle.valueOf((String) effectSummon.get("particle"));
            }
            catch (Exception any){
                particle = Particle.FLAME;
            }

            boolean isVector;

            try{
                isVector = (boolean) effectSummon.get("isVector");
            }
            catch (Exception any){
                isVector = false;
            }


            RadiusInterpret inter = new RadiusInterpret((Map<?, ?>) effectSummon.get("radius"));
            Location playerLoc = data.getSource();
            Location particleLoc;
            if(isVector){
                Vector vector = playerLoc.getDirection().normalize();
                Vector up = new Vector(0,1,0);

                Vector right = vector.crossProduct(up).normalize();

                double radian = Math.toRadians(inter.getAngle());

                Vector relate = right.clone().multiply(Math.cos(radian)).add(up.clone().multiply(Math.sin(radian))).multiply(inter.getDistance());
                particleLoc = playerLoc.clone().add(relate);
            }
            else {
                double x = inter.getDistance() * Math.cos(Math.toRadians(inter.getAngle()) + Math.toRadians(playerLoc.getYaw()));
                double z = inter.getDistance() * Math.sin(Math.toRadians(inter.getAngle()) + Math.toRadians(playerLoc.getYaw()));

                particleLoc = playerLoc.clone().add(x, inter.getY(), z);
            }

            data.getCast().getEntity().getWorld().spawnParticle(particle, particleLoc, count);
        }

        private void definedEffect(Map<?, ?> defined){
            String type = (String) defined.get("shape");
            RadiusInterpret inter = new RadiusInterpret((Map<?, ?>) defined.get("radius"));
            Particle particle;

            int count;

            try{
                count = (Integer) defined.get("count");
            }
            catch (Exception any){
                count = 0;
            }


            try{
                particle = Particle.valueOf((String) defined.get("particle"));
            }
            catch (Exception any){
                particle = Particle.FLAME;
            }

            Location location;
            boolean isCastLoc;
            boolean isVector;
            try{
                isCastLoc = (Boolean) defined.get("isCastLoc");
            }
            catch (Exception any){
                isCastLoc = false;
            }

            try{
                isVector = (Boolean) defined.get("isVector");
            }
            catch (Exception any){
                isVector = false;
            }

            if(isCastLoc){
                location = data.getCast().getEntity().getLocation();
            }
            else {
                location = data.getSource();
            }

            double angleMax;
            double add;

            try{
                angleMax = (Double) defined.get("angleMax");
                add = (Double) defined.get("add");
            }
            catch (Exception any){
                angleMax = 0;
                add = 0;
            }

            switch (type){
                case "CIRCLE" -> circle(location, particle, angleMax, add, inter, isVector, count);
                case "HELIX" -> helix();
            }
        }

        private void circle(Location location, Particle particle, double angleMax, double add, RadiusInterpret inter ,boolean onVector, int count){
            new BukkitRunnable(){
                double angle = 0;

                @Override
                public void run() {
                    if(location.getWorld() == null) return;
                    angle += add;

                    if(angle > angleMax){
                        cancel();
                        return;
                    }
                    double radian = Math.toRadians(angle + location.getYaw());
                    Location particleLoc;
                    if(onVector){
                        Vector locVec = location.getDirection().normalize();
                        Vector up = new Vector(0, 1, 0);

                        Vector another = locVec.clone().crossProduct(up).normalize();

                        Vector right = another.clone().multiply(Math.cos(angle)).add(locVec.multiply(Math.sin(angle))).normalize().multiply(inter.getDistance());
                        particleLoc = location.clone().add(right);
                    }
                    else {
                        double x = inter.getDistance() * Math.cos(radian);
                        double z = inter.getDistance() * Math.sin(radian);
                        particleLoc = location.clone().add(x, inter.getY(), z);
                    }

                    location.getWorld().spawnParticle(particle, particleLoc, count);
                }
            }.runTaskTimer(FrostAPI.getPlugin(), 0, 1);
        }

        private void vetcical_circle(){

        }

        private void helix(){

        }

        private void spiral(){
            
        }
    }

    private enum Shape{
        CIRCLE,

        VERTICAL_CIRCLE,

        HELIX,

        SPIRAL_OUTER,

        SPIRAL_INNER
    }
}
