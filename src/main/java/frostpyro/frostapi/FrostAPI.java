package frostpyro.frostapi;

import frostpyro.frostapi.listeners.Event;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class FrostAPI extends JavaPlugin {
    private static FrostAPI plugin;
    @Override
    public void onEnable() {
        plugin = this;
        loadConfigs();
        // Plugin startup logic
        generateSkillFolder();
        generateSkillBuildFolder();



        new Event(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static FrostAPI getPlugin(){
        return plugin;
    }
    private void generateSkillFolder(){
        File FOLDER = new File(getDataFolder(), "\\skill");
        if(FOLDER.exists()) return;
        FOLDER.mkdirs();
    }

    private void generateSkillBuildFolder(){
        File FOLDER = new File(getDataFolder(), "\\skillBuild");
        if(FOLDER.exists()) return;
        FOLDER.mkdirs();
    }

    private FileConfiguration skill, build, sql;
    private void loadConfigs(){
        File skill = new File(getDataFolder(), "skillName.yml"), build = new File(getDataFolder(), "skillBuildName.yml"), sql = new File(getDataFolder(), "SQLInform.yml");
        if(!skill.exists()){
            skill.getParentFile().mkdirs();
            saveResource("skillName.yml", false);
        }
        if(!build.exists()){
            build.getParentFile().mkdirs();
            saveResource("skillBuildName.yml", false);
        }
        if(!sql.exists()){
            sql.getParentFile().mkdirs();
            saveResource("SQLInform.yml", false);
        }
        this.skill = new YamlConfiguration();
        this.build = new YamlConfiguration();
        this.sql = new YamlConfiguration();
        try{
            this.skill.load(skill);
            this.build.load(build);
            this.sql.load(sql);
        }
        catch (IOException | InvalidConfigurationException e){
            e.printStackTrace();
            return;
        }
        getLogger().info("config enabled!");
    }

    public List<String> skillName(){
        File file = new File(getDataFolder(), "skillName.yml");
        FileConfiguration configuration = new YamlConfiguration();
        try{
            configuration.load(file);
        }
        catch (IOException|InvalidConfigurationException e){
            e.printStackTrace();
            return null;
        }
        return configuration.getStringList("skillNames");
    }
}
