package frostpyro.frostapi.util.skill.ymlSkill.run;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;

public interface Skill {
    Map<String, FileConfiguration> skillMapCache = new HashMap<>();
    void activateSkill();
}
