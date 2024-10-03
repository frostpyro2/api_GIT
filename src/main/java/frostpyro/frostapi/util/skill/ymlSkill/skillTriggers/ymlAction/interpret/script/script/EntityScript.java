package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.interpret.script.script;

import frostpyro.frostapi.util.skill.trigger.NonPlayerTrigger;
import frostpyro.frostapi.util.skill.trigger.PlayerTriggerData;
import frostpyro.frostapi.util.skill.trigger.TriggerData;
import frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.interpret.base.TriggeredConfig;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Map;

public class EntityScript implements SkillRunnable {

    /*

     */

    private Entity entity;
    private int delay = 0;
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

    private void setDelay(TriggerData data, Map<?, ?> script){
        if(!script.containsKey("delay")) return;
        Map<?, ?> inner = (Map<?, ?>) script.get("delay");
        try{
            if(inner.containsKey("addDelay")){
                delay += (int)inner.get("addDelay");
                return;
            }
            delay = (int)inner.get("setDelay");
        }
        catch (Exception any){
            //
        }
    }

    private boolean isNull(ConfigurationSection section){
        return section == null;
    }
}
