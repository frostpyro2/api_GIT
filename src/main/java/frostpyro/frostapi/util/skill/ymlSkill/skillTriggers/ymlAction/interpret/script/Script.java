package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.interpret.script;

import frostpyro.frostapi.util.skill.trigger.TriggerData;
import frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.interpret.base.TriggeredConfig;
import org.bukkit.configuration.ConfigurationSection;

public interface Script {
    void run(TriggeredConfig config);
}
