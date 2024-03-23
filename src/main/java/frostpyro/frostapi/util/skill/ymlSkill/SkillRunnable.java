package frostpyro.frostapi.util.skill.ymlSkill;

import frostpyro.frostapi.util.skill.trigger.TriggerData;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

public class SkillRunnable extends BukkitRunnable {
    public SkillConfig config;
    public TriggerData data;
    public SkillRunnable(SkillConfig config, TriggerData data){
        this.config = config;
        this.data = data;
    }
    @Override
    public void run() {

    }
}
