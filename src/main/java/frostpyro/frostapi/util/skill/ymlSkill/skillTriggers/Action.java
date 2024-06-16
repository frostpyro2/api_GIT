package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers;

import frostpyro.frostapi.FrostAPI;
import org.bukkit.scheduler.BukkitRunnable;

public interface Action {
    void section();
    default void skillRun(Runnable runnable, int delay){
        new BukkitRunnable(){
            @Override
            public void run() {
                runnable.run();
            }
        }.runTaskLater(FrostAPI.getPlugin(), delay);
    }
}
