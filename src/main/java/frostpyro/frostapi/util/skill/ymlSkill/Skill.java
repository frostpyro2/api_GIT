package frostpyro.frostapi.util.skill.ymlSkill;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.util.skill.trigger.TriggerData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Skill {
    private static List<FileConfiguration> skillList = new ArrayList<>();
    private static List<String> skillFileNames = new ArrayList<>();
    private File file;
    private SkillConfig configuration;
    private String fileName;
    private TriggerData data;

    public Skill(String fileName, TriggerData data){
        this.fileName = fileName;
        file = new File(FrostAPI.getPlugin().getDataFolder(), "skill\\skills\\" + fileName + ".yml");
        try{
            configuration = new SkillConfig(file);
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

    }

    public static void registerSkill(){
        File file = new File(FrostAPI.getPlugin().getDataFolder(), "skill\\skills");
        File[] skillFiles = file.listFiles();
        if(skillFiles == null) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "skill file does not exist!");
            return;
        }
        if(skillFiles.length == 0){
            Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "skill file is empty!");
            return;
        }
        for(File skillFile : skillFiles){
            FileConfiguration skillConf = new YamlConfiguration();
            try{
                skillConf.load(skillFile);
            }
            catch (Exception any){
                Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "skillFile load failed" + file.getName());
                continue;
            }
            skillFileNames.add(skillFile.getName());
            skillList.add(skillConf);
            String[] strings = skillFile.getName().split("\\.");
            Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_AQUA + "skill added:" + strings[0]);
        }
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "--------------------------------");
    }
}
