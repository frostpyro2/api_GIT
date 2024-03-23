package frostpyro.frostapi.util.skill.ymlSkill;

import frostpyro.frostapi.util.skill.armorStand.ArmorStandItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.checkerframework.checker.units.qual.A;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class SkillConfig extends YamlConfiguration{
    public SkillConfig(File file) throws IOException, InvalidConfigurationException {
        this.load(file);
    }

    public String getTest(){
        return getString("announce");
    }

    public double getDamage(){
        return getDouble("damage");
    }

    public long getDelay(){
        return getLong("delay");
    }

    public double getRadius(){
        return getDouble("radius");
    }

    public List<?> getSummon(){
        return getList("summon");
    }

    public String getSound(){
        return getString("sound");
    }
}
