package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.interpret.script.script;

import frostpyro.frostapi.util.skill.trigger.TriggerData;
import org.bukkit.configuration.ConfigurationSection;

public class DamageScript implements SkillRunnable{
    @Override
    public void run(TriggerData data, ConfigurationSection section){
        if(section == null) return;
    }
}
