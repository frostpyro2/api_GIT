package frostpyro.frostapi.util.lib;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.dataManage.stat.StatProvider;
import frostpyro.frostapi.dataManage.stat.nonPlayer.EntityData;
import frostpyro.frostapi.dataManage.stat.player.PlayerData;
import frostpyro.frostapi.util.skill.trigger.NonPlayerTrigger;
import frostpyro.frostapi.util.skill.trigger.PlayerTriggerData;
import frostpyro.frostapi.util.skill.trigger.TriggerData;
import frostpyro.frostapi.util.skill.ymlSkill.run.Skill;
import frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.interpret.base.TriggeredConfig;
import frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.interpret.script.SkillThread;
import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
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

    public static void runSKill(TriggerData provider, String name){
        Configuration section = Skill.getSkill(name);
        ConfigurationSection skillSection = section.getConfigurationSection(name);
        if(provider instanceof PlayerTriggerData dt){
            new SkillThread(new TriggeredConfig(dt, skillSection)).run();
        }
        else if(provider instanceof NonPlayerTrigger dt){
            new SkillThread(new TriggeredConfig(dt, skillSection)).run();
        }
    }
}
