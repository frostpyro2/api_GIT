package frostpyro.frostapi.util.skill.casting;

import frostpyro.frostapi.dataManage.stat.data.PlayerFile;
import frostpyro.frostapi.util.skill.SkillManager;
import frostpyro.frostapi.util.skill.trigger.PlayerTriggerData;
import frostpyro.frostapi.util.skill.ymlSkill.run.PlayerSkill;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;

public class AnotherTrigger extends SkillManager {
    public AnotherTrigger(PlayerTriggerData data) {
        super(data);
    }

    @Override
    public void cast() {
        PlayerTriggerData data = data();
        Player player = (Player) data.getTmp().getEntity();
        FileConfiguration file = PlayerFile.getFile(player);
        ConfigurationSection section = file.getConfigurationSection("skill");
        if(section == null){
            player.sendMessage("no skill section!");
            return;
        }
        List<?> skills = section.getList(data.getType().getType());
        if(skills == null) return;
        for(Object skill : skills){
            if(skill instanceof String str)
                new PlayerSkill(str, data).activateSkill();
        }
    }
}
