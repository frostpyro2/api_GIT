package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.util.skill.trigger.PlayerTriggerData;
import frostpyro.frostapi.util.skill.ymlSkill.yamlInterpret.PathName;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Map;

public class ProjectileAction implements Action{
    private FileConfiguration configuration;
    private PlayerTriggerData data;
    private String path;
    public ProjectileAction(FileConfiguration configuration, PlayerTriggerData data){
        this.configuration = configuration;
        this.data = data;
        path = PathName.PROJECTILE.getActualPath();
    }

    public ProjectileAction(FileConfiguration configuration, PlayerTriggerData data, String path){
        this.configuration = configuration;
        this.data = data;
        this.path = path;
    }
    @Override
    public void section() {
        new ProjectileAction.Projectile(data, configuration.getList(path)).runTask(FrostAPI.getPlugin());
    }

    //TODO: 투사체 완성시키기

    private static class Projectile extends BukkitRunnable {
        private PlayerTriggerData data;
        private List<?> skillAct;
        Projectile(PlayerTriggerData data, List<?> skillAct){
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
