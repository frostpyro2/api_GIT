package frostpyro.frostapi.api.skill.config;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;

public class SkillConfig extends YamlConfiguration {
    private File file;
    public SkillConfig(File file){
        this.file = file;
        try{
            this.load(file);
        }
        catch (IOException| InvalidConfigurationException e){
            throw new RuntimeException(e);
        }
    }
}
