package frostpyro.frostapi.util.skill.ymlSkill;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.api.listeners.customEvents.attackEvents.AttackEvent;
import frostpyro.frostapi.util.skill.SkillManager;
import frostpyro.frostapi.util.skill.trigger.TriggerData;
import frostpyro.frostapi.util.skill.trigger.TriggerType;
import frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class Skill implements Listener{
    private static Map<String,FileConfiguration> skillMap = new HashMap<>();
    private File file;
    private FileConfiguration configuration;
    private final Map<FileConfiguration, Map<String, FileConfiguration>> toggle = new HashMap<>();
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

    public void activateSkill() {
        if(data.getData() != null){
            if(FrostAPI.getPlugin().isDamagedBySkill(data.getData().getTarget())){
                FrostAPI.getPlugin().removeEntityDamageKey(data.getData().getTarget());
                return;
            }
        }
        if(configuration == null) return;
        skillMechanism();
    }

    private void skillMechanism(){
        toggleSkill();

        if(!data.getCast().getToggleMap().isEmpty()){
            Bukkit.getConsoleSender().sendMessage(data.getType().getType());
            Map<String, FileConfiguration> toggleCheck = data.getCast().getToggleMap().get(data.getCast().getToggle());
            if(toggleCheck == null){
                Bukkit.getConsoleSender().sendMessage("toggleCheck is NULL");
                return;
            }
            for(String str : toggleCheck.keySet()){
                Bukkit.getConsoleSender().sendMessage(str);
            }
            if(toggleCheck.containsKey(data.getType().getType())){
                configuration = toggleCheck.get(data.getType().getType());
            }
        }

        if(data.getCast().isCoolDown(configuration)) {
            try{
                data.getEvent().setCancelled(true);
            }
            catch (Exception any){
                //none
            }
            return;
        }



        data.getCast().removeCoolDown(configuration);

        double coolDown = 0;

        try{
            coolDown = configuration.getDouble("skill.coolDown");
        }
        catch (Exception any){
            //do nothing
        }

        data.getCast().setCoolDown(configuration, coolDown);

        Activation activation = new Activation(configuration, data);
        for(Action skillAct : activation.actions()){
            if(skillAct == null) continue;
            skillAct.section();
        }
    }

    private void toggleSkill(){
        boolean isToggle;

        try{
            isToggle = configuration.getBoolean("skill.isToggle");
        }
        catch (Exception any){
            return;
        }

        if(!isToggle) {
            Bukkit.getConsoleSender().sendMessage("is not toggle");
            return;
        }

        if(data.getCast().getToggle() != null){
            if(data.getCast().notDuration(data.getCast().getToggle())){
                data.getCast().removeDuration(data.getCast().getToggle());
                data.getCast().setToggle(null);
                data.getCast().removeToggle(data.getCast().getToggle());
            }
            return;
        }

        Bukkit.getConsoleSender().sendMessage("toggleTrigger is null");

        Map<String, FileConfiguration> tmp = new HashMap<>();

        for(String skillTrigger: TriggerType.getKeys()){
            FileConfiguration skillConfig;
            String skill;
            try{
                skill = configuration.getString("skill."+skillTrigger);
                if(skill == null) continue;
                File skillFileTmp = new File(FrostAPI.getPlugin().getDataFolder(), "skill\\skills" + skill + ".yml");
                skillConfig = YamlConfiguration.loadConfiguration(skillFileTmp);
                tmp.put(skillTrigger, skillConfig);
            }
            catch (Exception e){
                Bukkit.getConsoleSender().sendMessage(skillTrigger);
            }
        }

        double duration;

        try{
            duration = configuration.getDouble("skill.duration");
        }
        catch (Exception e){
            duration = 0;
        }
        data.getCast().setToggle(configuration);
        data.getCast().setDuration(configuration, duration);
        data.getCast().registerToggle(configuration, tmp);
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
