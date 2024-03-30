package frostpyro.frostapi.util.skill.ymlSkill;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.util.skill.SkillManager;
import frostpyro.frostapi.util.skill.trigger.TriggerData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Skill {
    private static Map<String,FileConfiguration> skillMap = new HashMap<>();
    private File file;
    private FileConfiguration configuration;
    private TriggerData data;

    public Skill(@NotNull String fileName, TriggerData data){
        try{
            configuration = skillMap.computeIfAbsent(fileName, NONE -> null);
        }
        catch (Exception any){
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "failed to load skill file!");
        }
        this.data = data;
    }

    public Skill(SkillConfig fileConfig, TriggerData data){
        this.configuration = fileConfig;
        this.data = data;
    }

    public void activateSkill() {
        if(configuration == null) return;
        SkillAction action = new SkillAction(configuration, data);
        action.actionSection();
    }

    public static void registerSkill(){
        for(String fileName : SkillManager.getSkills()){
            File file = new File(FrostAPI.getPlugin().getDataFolder(), "skill\\skills\\" + fileName + ".yml");
            FileConfiguration fileConfig = new YamlConfiguration();
            try{
                fileConfig.load(file);
            }
            catch (Exception any){
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "failed to load:" + fileName);
                continue;
            }
            skillMap.put(fileName, fileConfig);
            Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "skill yml successfully added:" + file.getName());
        }
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "--------------------------------");
    }
}
