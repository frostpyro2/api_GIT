package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.interpret.script.script;

import frostpyro.frostapi.util.skill.trigger.TriggerData;
import org.bukkit.configuration.ConfigurationSection;

public interface SkillRunnable {
    void run(TriggerData data, ConfigurationSection section);
}
