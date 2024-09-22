package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.interpret.script.script;

import frostpyro.frostapi.util.skill.trigger.TriggerData;
import frostpyro.frostapi.util.skill.ymlSkill.run.Skill;
import frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.interpret.base.TriggeredConfig;
import frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.interpret.script.SkillThread;
import org.bukkit.configuration.ConfigurationSection;

public class ExternalSkillScript implements SkillRunnable{
    @Override
    public void run(TriggerData data, ConfigurationSection section) {
        if(section == null) return;
        externalSkill(data, section);
    }

    private void externalSkill(TriggerData data, ConfigurationSection section){
        String skillName = section.getString("externalSkill");
        try{
            new SkillThread(new TriggeredConfig(data, Skill.skillsYml().getConfigurationSection(skillName))).run();
        }
        catch (Exception any){
            //none
        }
    }
}
