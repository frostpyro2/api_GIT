package frostpyro.frostapi.util.skill.casting;

import frostpyro.frostapi.dataManage.stat.data.PlayerFile;
import frostpyro.frostapi.util.skill.SkillManager;
import frostpyro.frostapi.util.skill.trigger.PlayerTriggerData;
import frostpyro.frostapi.util.skill.ymlSkill.run.PlayerSkill;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;
import java.util.Map;

public class AnotherTrigger extends SkillManager {
    public AnotherTrigger(PlayerTriggerData data) {
        super(data);
    }

    @Override
    public void cast() {
        FileConfiguration playerFile = PlayerFile.getFile(data().getCast().getEntity());
        ConfigurationSection skillSection = playerFile.getConfigurationSection("skill");
        if(skillSection == null) return;
        List<String> skillFile = skillSection.getStringList(data().getType().getType());
        for(String skill : skillFile){

        }
    }
}
