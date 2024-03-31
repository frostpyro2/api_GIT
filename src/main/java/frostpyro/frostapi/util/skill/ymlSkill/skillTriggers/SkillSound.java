package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.util.skill.trigger.TriggerData;
import frostpyro.frostapi.util.skill.ymlSkill.yamlInterpret.RadiusInterpret;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Map;

public class SkillSound {
    private TriggerData data;
    private Configuration configuration;
    public SkillSound(Configuration configuration, TriggerData data){
        this.data = data;
        this.configuration = configuration;
    }

    public void soundSection(){
        new SkillSound.Skill(data, configuration.getMapList("skill.sound")).runTaskTimer(FrostAPI.getPlugin(), 0, 1);
    }

    private static class Skill extends BukkitRunnable{
        private int delay = 0;
        private int index = 0;
        private TriggerData data;
        private  List<Map<?, ?>> act;
        Skill(TriggerData data, List<Map<?, ?>> act){
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

            Map<?, ?> action = act.get(index++);

            if(action.containsKey("sound")){
                Map<?, ?> sound = (Map<?, ?>) action.get("sound");
                sound(sound);
            }
            else if(action.containsKey("delay")){
                delay = (int) action.get("delay");
            }
        }

        private void sound(Map<?, ?> sound){
            float volume;
            float pitch;
            try{
                volume = (float) sound.get("volume");
                pitch = (float) sound.get("pitch");
            }
            catch (Exception any){
                volume = 1;
                pitch = 1;
            }

            float finalVol = volume;
            float finalPit = pitch;

            String target = (String) sound.get("target");
            Sound play;
            try{
                play = Sound.valueOf((String) sound.get("sound"));
            }
            catch (Exception any){
                return;
            }

            if("self".equals(target)) {
                data.getCast().getEntity().getWorld().playSound(data.getCast().getEntity().getLocation(), play, volume, pitch);
                return;
            }

            RadiusInterpret inter = new RadiusInterpret(sound);

            Location center = data.getCast().getEntity().getLocation();

            if(center.getWorld() == null) return;

            center.getWorld().getNearbyEntities(center, inter.getX(), inter.getY(), inter.getZ()).forEach(entity -> {
                if(entity instanceof LivingEntity living){
                    if(living != data.getCast().getEntity()){
                        living.getWorld().playSound(living.getLocation(), play, finalVol, finalPit);
                    }
                }
            });
        }
    }
}
