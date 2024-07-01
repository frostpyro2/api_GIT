package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.interpret;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.util.skill.trigger.TriggerData;
import org.bukkit.configuration.Configuration;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Map;

public class YamlInterpret {
    Configuration config;
     TriggerData data;
     String path;
     int delay;

    public YamlInterpret(Configuration config, TriggerData data, String path){
        this.config = config;
        this.data = data;
        this.path = path;
    }

    public void skillRun(){

    }

    private static class InterpretEngine{
        List<?> objects;
        int delay = 0;
        public InterpretEngine(List<?> objects, TriggerData data){
            this.objects = objects;
        }

        private void delay(Map<?, ?> delayMap){
            try{
                delay += (int)delayMap.get("addDelay");
                delay = (int)delayMap.get("setDelay");
            }
            catch (Exception any){
                //no needs to catch any exception
            }
        }

        private void damage(Map<?, ?> damageMap){

        }


        private void run(Runnable runnable, int delay){
            new BukkitRunnable(){
                @Override
                public void run() {
                    runnable.run();
                }
            }.runTaskLater(FrostAPI.getPlugin(), delay);
        }
    }
}
