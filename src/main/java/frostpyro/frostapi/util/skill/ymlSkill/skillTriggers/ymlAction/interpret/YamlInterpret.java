package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.interpret;

import frostpyro.frostapi.util.skill.trigger.TriggerData;
import org.bukkit.configuration.Configuration;

public class YamlInterpret {
    Configuration config;
     TriggerData data;
     String path;
    int delay;
    public YamlInterpret(Configuration config, TriggerData data, String path){
        this.config = config;
        this.data = data;
        this.path = path;
    }

    
}
