package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.util.skill.trigger.TriggerData;
import org.bukkit.configuration.Configuration;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Objects;

public class SkillArmorStand {
    private Configuration configuration;
    private TriggerData data;
    public SkillArmorStand(Configuration configuration, TriggerData data){
        this.configuration = configuration;
        this.data = data;
    }

    public void skillArmorSection(){
        new ArmorStand(configuration.getList("skill.armorStand"), data).runTaskTimer(FrostAPI.getPlugin(), 0, 1);
    }
    private static class ArmorStand extends BukkitRunnable{
        private int index = 0;
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
            if(delay > 0){
                delay--;
                return;
            }

            if(index >= act.size()){
                this.cancel();
                return;
            }

            Object obj = act.get(index++);
        }
    }
}
