package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.interpret.script.script;

import frostpyro.frostapi.util.skill.trigger.TriggerData;
import org.bukkit.configuration.ConfigurationSection;

import java.util.Map;

public class SoundScript implements SkillRunnable{
    private int delay = 0;
    @Override
    public void run(TriggerData data, ConfigurationSection section) {

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
