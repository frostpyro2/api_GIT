package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.util.skill.trigger.TriggerData;
import frostpyro.frostapi.util.skill.ymlSkill.yamlInterpret.RadiusInterpret;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.configuration.Configuration;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.Map;

public class SkillEffect {
    private TriggerData data;
    private Configuration configuration;
    public SkillEffect(Configuration configuration, TriggerData data){
        this.data = data;
        this.configuration = configuration;
    }

    public void effectSection(){
        new Effect(data, configuration.getList("skill.effect")).runTaskTimer(FrostAPI.getPlugin(), 0, 1);
    }

    private static class Effect extends BukkitRunnable{
        private int delay = 0;
        private int index = 0;

        private TriggerData data;
        private List<?> act;
        Effect(TriggerData data, List<?> act){
            this.data = data;
            this.act = act;
        }
        @Override
        public void run() {
            if(act == null){
                this.cancel();
                return;
            }
            if(delay > 0){
                delay--;
                return;
            }

            if(index >= act.size()){
                this.cancel();
                return;
            }

            Object effect = act.get(index++);

            if(effect instanceof Map<?,?> effectMap){
                if(effectMap.containsKey("effect")){
                    Map<?, ?> particleMap = (Map<?, ?>) effectMap.get("effect");
                    effect(particleMap);
                }
                if(effectMap.containsKey("delay")){
                    delay = (int) effectMap.get("delay");
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

            RadiusInterpret inter = new RadiusInterpret(effectSummon);

            Location playerLoc = data.getCast().getEntity().getLocation();
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
                double x = inter.getDistance() * Math.cos(Math.toRadians(inter.getAngle()));
                double z = inter.getDistance() * Math.sin(Math.toRadians(inter.getAngle()));

                double rotatedX = x * Math.cos(playerLoc.getYaw()-90) - z * Math.sin(playerLoc.getYaw()-90);
                double rotatedZ = x * Math.cos(playerLoc.getY()-90) + z * Math.sin(playerLoc.getYaw()-90);
                particleLoc = playerLoc.clone().add(rotatedX, inter.getY(), rotatedZ);
            }

            data.getCast().getEntity().getWorld().spawnParticle(particle, particleLoc, count);
        }
    }
}
