package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.interpret.script.script;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.util.lib.Utility;
import frostpyro.frostapi.util.skill.trigger.TriggerData;
import frostpyro.frostapi.util.skill.ymlSkill.run.Skill;
import frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.interpret.base.TriggeredConfig;
import frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.interpret.script.SkillThread;
import jdk.jshell.spi.ExecutionControlProvider;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class ExternalSkillScript implements SkillRunnable{
    private int delay = 0;

    @Override
    public void run(TriggerData data, ConfigurationSection section) {
        if(section == null) return;
        List<?> scripts = section.getList("externalSkill");
        if(scripts == null){
            return;
        }

        for(Object script : scripts){
            if(script instanceof Map<?, ?> mapScript){
                Bukkit.getServer().getScheduler().runTaskLater(FrostAPI.getPlugin(), ()->{
                    externalSkill(data, mapScript);
                }, delay);
                setDelay(data, mapScript);
            }
        }
    }

    private void externalSkill(TriggerData data, Map<?, ?> script){
        try{
            String externalSkill = (String) script.get("externalSkill");
            Utility.runSKill(data, externalSkill);
        }
        catch (Exception any){
            //
        }
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
}
