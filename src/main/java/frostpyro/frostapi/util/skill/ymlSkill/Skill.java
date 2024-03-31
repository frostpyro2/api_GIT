package frostpyro.frostapi.util.skill.ymlSkill;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.util.skill.SkillManager;
import frostpyro.frostapi.util.skill.trigger.TriggerData;
import frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.SkillAction;
import frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.SkillEffect;
import frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.SkillSound;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.HashMap;
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
        if(data.getData() != null){
            if(FrostAPI.getPlugin().isDamagedBySkill(data.getData().getTarget())){
                FrostAPI.getPlugin().removeEntityDamageKey(data.getData().getTarget());
                return;
            }
        }
        if(configuration == null) return;
        new SkillAction(configuration, data).actionSection();
        new SkillSound(configuration, data).soundSection();
        new SkillEffect(configuration, data).effectSection();
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
