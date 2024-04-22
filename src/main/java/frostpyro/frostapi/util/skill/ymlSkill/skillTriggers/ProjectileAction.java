package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.util.skill.trigger.TriggerData;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class ProjectileAction implements Action{
    private FileConfiguration configuration;
    private TriggerData data;
    public ProjectileAction(FileConfiguration configuration, TriggerData data){
        this.configuration = configuration;
        this.data = data;
    }
    @Override
    public void section() {
        new ProjectileAction.Projectile(data, configuration.getList("skill.projectile")).runTask(FrostAPI.getPlugin());
    }

    private static class Projectile extends BukkitRunnable {
        private TriggerData data;
        private List<?> skillAct;
        Projectile(TriggerData data, List<?> skillAct){
            this.data = data;
            this.skillAct = skillAct;
        }

        private int delay = 0;
        @Override
        public void run() {
            if(skillAct == null) return;
        }
    }

}
