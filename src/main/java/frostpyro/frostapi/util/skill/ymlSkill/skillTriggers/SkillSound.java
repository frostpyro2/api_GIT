package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.util.skill.trigger.PlayerTriggerData;
import frostpyro.frostapi.util.skill.ymlSkill.yamlInterpret.RadiusInterpret;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;

public class SkillSound implements Action{
    private PlayerTriggerData data;
    private Configuration configuration;
    private String path;
    public SkillSound(Configuration configuration, PlayerTriggerData data){
        this.data = data;
        this.configuration = configuration;
    }

    public void section(){
        WeakReference<SkillSound.Skill> skillSound = new WeakReference<>(new SkillSound.Skill(data, configuration.getMapList("skill.sound")));
        if(skillSound.get() == null) return;
        skillSound.get().runTask(FrostAPI.getPlugin());
    }

    private static class Skill extends BukkitRunnable{
        private PlayerTriggerData data;
        private  List<Map<?, ?>> act;
        private int delay = 0;
        Skill(PlayerTriggerData data, List<Map<?, ?>> act){
            this.data = data;
            this.act = act;
        }
        @Override
        public void run() {
            if(act == null){
                this.cancel();
                return;
            }

            for(Map<?, ?> soundMap : act){
                new BukkitRunnable(){
                    @Override
                    public void run() {
                        sound(soundMap);
                    }
                }.runTaskLater(FrostAPI.getPlugin(), delay);
                delay(soundMap);
            }
        }

        private void sound(Map<?, ?> sound){
            if(!sound.containsKey("sound")) return;
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

        private void delay(Map<?, ?> soundMap){
            if(!soundMap.containsKey("delay")) return;
            delay = (int) soundMap.get("delay");
        }
    }
}
