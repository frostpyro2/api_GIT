package frostpyro.frostapi.util.skill.ymlSkill.run;

import frostpyro.frostapi.util.skill.trigger.NonPlayerTrigger;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

public class NonPlayerSkill implements Skill{
    private ConfigurationSection skillConfig;
    private NonPlayerTrigger nonPlayer;
    public NonPlayerSkill(String fileName, NonPlayerTrigger nonPlayer){
        this.nonPlayer = nonPlayer;
    }
    @Override
    public void activateSkill() {

    }
}
