package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.interpret.script.script;

import frostpyro.frostapi.util.skill.trigger.NonPlayerTrigger;
import frostpyro.frostapi.util.skill.trigger.PlayerTriggerData;
import frostpyro.frostapi.util.skill.trigger.TriggerData;
import frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.interpret.base.TriggeredConfig;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;

public class EntityScript implements SkillRunnable {

    /*

     */

    private Entity entity;

    @Override
    public void run(TriggerData data, ConfigurationSection section) {
        if(section == null) return;

    }
    private void damage(TriggerData data ,ConfigurationSection section){
        if(data instanceof PlayerTriggerData dt){


        }
        else if(data instanceof NonPlayerTrigger dt){

        }
    }

    private void summon(TriggerData data, ConfigurationSection section){

    }

    private boolean isNull(ConfigurationSection section){
        return section == null;
    }
}
