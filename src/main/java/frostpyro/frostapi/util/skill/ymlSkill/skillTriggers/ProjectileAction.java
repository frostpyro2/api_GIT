package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.util.skill.trigger.TriggerData;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Snowball;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Map;

public class ProjectileAction implements Action{
    private FileConfiguration configuration;
    private TriggerData data;
    private String path;
    public ProjectileAction(FileConfiguration configuration, TriggerData data){
        this.configuration = configuration;
        this.data = data;
        path = "skill.projectile";
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

            for(Object obj : skillAct){
                if(obj instanceof Map<?, ?> setting){
                    delay(setting);
                    skillRun(()->projectileSet(setting), delay);
                }
            }
        }
        private void delay(Map<?, ?> setting){
            if(!setting.containsKey("delay")) return;
            try{
                delay = (Integer)setting.get("delay");
            }
            catch (Exception any){
                delay = 0;
            }
        }
        private void projectileSet(Map<?, ?> setting){
            if(!setting.containsKey("projectile")) return;
            
        }

        private void skillRun(Runnable runnable, int delay){
            new BukkitRunnable(){
                @Override
                public void run() {
                    runnable.run();
                }
            }.runTaskLater(FrostAPI.getPlugin(), delay);
        }
    }

}
