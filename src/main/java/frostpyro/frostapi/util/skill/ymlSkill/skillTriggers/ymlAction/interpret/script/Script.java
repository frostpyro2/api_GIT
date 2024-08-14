package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.interpret.script;

import frostpyro.frostapi.util.skill.trigger.TriggerData;
import frostpyro.frostapi.util.skill.ymlSkill.run.Skill;
import frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.interpret.base.TriggeredConfig;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface Script {
    List<? extends Script> interpret = new ArrayList<>(Arrays.asList(new SkillActScript(), new EntityScript()));
    void run(TriggeredConfig config);
}
