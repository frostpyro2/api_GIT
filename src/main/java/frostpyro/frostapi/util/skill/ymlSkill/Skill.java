package frostpyro.frostapi.util.skill.ymlSkill;

import frostpyro.frostapi.FrostAPI;
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
    private FileConfiguration configuration;

    public Skill(String fileName){
        file = new File(FrostAPI.getPlugin().getDataFolder(), "skill//skills" + fileName + ".yml");
        configuration = new YamlConfiguration();
        try{
            configuration.load(file);
        }
        catch (Exception any){
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "failed to load skill file!");
        }
    }

    public Skill(FileConfiguration fileConfig){
        this.configuration = fileConfig;
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
                continue;
            }
            skillFileNames.add(skillFile.getName());
            skillList.add(skillConf);
        }
    }
}
