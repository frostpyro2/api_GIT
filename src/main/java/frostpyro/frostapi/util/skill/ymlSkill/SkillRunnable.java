package frostpyro.frostapi.util.skill.ymlSkill;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

public class SkillRunnable extends BukkitRunnable {
    public SkillConfig config;
    public SkillRunnable(SkillConfig config){
        this.config = config;
    }
    @Override
    public void run() {

    }
}
