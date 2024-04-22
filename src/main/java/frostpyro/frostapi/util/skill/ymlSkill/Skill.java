package frostpyro.frostapi.util.skill.ymlSkill;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.api.damageManager.damageData.DamageType;
import frostpyro.frostapi.api.listeners.customEvents.attackEvents.AttackEvent;
import frostpyro.frostapi.util.skill.SkillManager;
import frostpyro.frostapi.util.skill.casting.AnotherTrigger;
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
import org.bukkit.event.entity.EntityDamageEvent;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class Skill implements Listener{
    private static Map<String,FileConfiguration> skillMap = new HashMap<>();
    private Set<TriggerData> suppressTrigger = new HashSet<>();
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
        if(configuration == null) return;
        skillMechanism();
    }

    private void skillMechanism(){
        if(data.getEvent() != null){
            if(FrostAPI.getPlugin().damagedEntity(data.getEvent().getEntity().getUniqueId())){
                FrostAPI.getPlugin().removeEntity(data.getEvent().getEntity().getUniqueId());
                data.getEvent().getAttack().getDamage().setDamageType(DamageType.MELEESKILL);
            }
        }

        if(data.getCast().isCoolDown(configuration)) {
            try{
                if(!data.getEvent().getAttack().getDamage().getDamageType().contains(DamageType.PHYSICAL)) return;
                data.getEvent().setCancelled(true);
            }
            catch (Exception e){
                //
            }
            return;
        }

        toggleSkill();

        if(!data.getCast().getToggleMap().isEmpty()){
            Map<String, FileConfiguration> toggleCheck = data.getCast().getToggleMap().get(data.getCast().getToggle());
            if(toggleCheck == null){
                return;
            }
            if(toggleCheck.containsKey(data.getType().getType())){
                configuration = toggleCheck.get(data.getType().getType());
            }
        }



        data.getCast().removeCoolDown(configuration);

        double coolDown ;

        try{
            coolDown = configuration.getDouble("skill.coolDown");
        }
        catch (Exception any){
            coolDown = .01;
        }

        Activation activation = new Activation(configuration, data);

        for(Action skillAct : activation.actions()){
            if(skillAct == null) continue;
            skillAct.section();
        }

        data.getCast().setCoolDown(configuration, coolDown);
    }

    private void toggleSkill(){

        if(data.getCast().getToggle() != null){
            if(data.getCast().notDuration(data.getCast().getToggle())){
                data.getCast().removeDuration(data.getCast().getToggle());
                data.getCast().setToggle(null);
                data.getCast().clearToggleCache();
            }
            return;
        }
        boolean isToggle;

        try{
            isToggle = configuration.getBoolean("skill.isToggle");
        }
        catch (Exception any){
            return;
        }

        if(!isToggle) {
            return;
        }

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
        if(data.getCast().getToggle() != null){
            data.getCast().removeToggle(data.getCast().getToggle());
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
