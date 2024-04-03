package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.util.skill.trigger.TriggerData;
import org.bukkit.configuration.Configuration;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SkillArmorStand {
    private Configuration configuration;
    private TriggerData data;
    public SkillArmorStand(Configuration configuration, TriggerData data){
        this.configuration = configuration;
        this.data = data;
    }

    public void skillArmorSection(){
        new ArmorStand(configuration.getList("skill.armorStand"), data).runTask(FrostAPI.getPlugin());
    }
    private static class ArmorStand extends BukkitRunnable{
        private int delay = 0;
        private List<?> act;
        private TriggerData data;
        ArmorStand(List<?> act, TriggerData data){
            this.act = act;
            this.data = data;
        }
        @Override
        public void run() {
            if(act == null){
                this.cancel();
                return;
            }
            for(Object obj : act){
                if(obj instanceof Map<?,?> standData){
                    if(standData.containsKey("delay")){
                        delay = (int) standData.get("delay");
                    }
                }
            }
        }
    }
}
