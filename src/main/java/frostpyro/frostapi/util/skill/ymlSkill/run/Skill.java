package frostpyro.frostapi.util.skill.ymlSkill.run;

import frostpyro.frostapi.FrostAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public interface Skill {
    static Map<String, Configuration> cache = new HashMap<>();
    void activateSkill();

    @Nullable
    static Configuration skillsYml(){
        File file = new File(FrostAPI.getPlugin().getDataFolder(), "\\skill\\skill.yml");
        if(!file.exists()) return null;
        return YamlConfiguration.loadConfiguration(file);
    }

    static void addSkillCache(){
        File file = new File(FrostAPI.getPlugin().getDataFolder(), "\\skill\\skills");
        if(!file.exists()) return;
        if(file.listFiles() == null) return;
        for(File skill : file.listFiles()){
            try{
                FileConfiguration fileConfig = YamlConfiguration.loadConfiguration(skill);
                String name = skill.getName().split("\\.")[0];
                cache.put(name, fileConfig);
                Bukkit.getConsoleSender().sendMessage("    "+ChatColor.GREEN+"loaded skill file: " +skill.getName());
            }
            catch (Exception any){
                Bukkit.getConsoleSender().sendMessage("    "+ ChatColor.RED +"error while loading config: "+skill.getName());
            }
        }
    }

    static void flushAll(){
        cache.clear();
    }

    static Configuration getSkill(String fileName){
        return cache.get(fileName);
    }
}
