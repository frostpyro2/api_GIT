package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.interpret.base;

import frostpyro.frostapi.util.skill.trigger.TriggerData;
import org.bukkit.configuration.ConfigurationSection;

public class TriggeredConfig {
    private TriggerData data;
    private ConfigurationSection section;

    public TriggeredConfig(TriggerData data, ConfigurationSection section){
        this.data = data;
        this.section = section;
    }

    public TriggerData getData(){
        return data;
    }

    public ConfigurationSection getSection(){
        return section;
    }
}
