package frostpyro.frostapi.util.skill.ymlSkill.run;

import frostpyro.frostapi.FrostAPI;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public interface Skill {
    void activateSkill();

    @Nullable
    static Configuration skillsYml(){
        File file = new File(FrostAPI.getPlugin().getDataFolder(), "\\skill\\skill.yml");
        if(!file.exists()) return null;
        return YamlConfiguration.loadConfiguration(file);
    }
}
