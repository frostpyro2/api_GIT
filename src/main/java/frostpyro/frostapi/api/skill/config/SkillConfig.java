package frostpyro.frostapi.api.skill.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;

public class SkillConfig extends YamlConfiguration {
    private File file;
    public SkillConfig(File file) {
        this.file = file;
    }
}
