package frostpyro.frostapi.util.lib;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.dataManage.stat.StatProvider;
import frostpyro.frostapi.dataManage.stat.nonPlayer.EntityData;
import frostpyro.frostapi.dataManage.stat.player.PlayerData;
import frostpyro.frostapi.util.skill.trigger.NonPlayerTrigger;
import frostpyro.frostapi.util.skill.trigger.PlayerTriggerData;
import frostpyro.frostapi.util.skill.trigger.TriggerData;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

public class Utility {
    public static boolean isFake(@NotNull Event event){
        return FrostAPI.getPlugin().fakeEventManager().isFake(event);
    }

    public static void skillAction(Runnable function, int delay){
        new BukkitRunnable(){
            @Override
            public void run() {
                function.run();
            }
        }.runTaskLater(FrostAPI.getPlugin(), delay);
    }

    public static void runSKill(TriggerData provider, ConfigurationSection section){
        if(provider instanceof PlayerTriggerData dt){

        }
        else if(provider instanceof NonPlayerTrigger dt){

        }
    }
}
