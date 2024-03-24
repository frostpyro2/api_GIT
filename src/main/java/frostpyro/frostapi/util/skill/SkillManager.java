package frostpyro.frostapi.util.skill;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.util.skill.trigger.TriggerData;
import frostpyro.frostapi.util.skill.trigger.TriggerType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Particle;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class SkillManager{
    private static List<String> skills = new ArrayList<>();

    private TriggerData data;

    public SkillManager(TriggerData data){
        this.data = data;
    }

    public abstract void cast();

    public TriggerData data(){
        return data;
    }

    public static List<String> getSkills(){
        return skills;
    }

    public static void registerSkill(){
        File dir = new File(FrostAPI.getPlugin().getDataFolder(), "skill\\skills");
        File[] skills = dir.listFiles();
        if(skills == null) return;
        for(File skill : skills) {
            String fileName = skill.getName();
            String[] actualName = fileName.split("\\.");
            SkillManager.skills.add(actualName[0]);
            Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_AQUA + "skill dir added:"+ actualName[0]);

        }
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "--------------------------------");
    }
}
