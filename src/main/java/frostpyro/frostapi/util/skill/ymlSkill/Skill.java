package frostpyro.frostapi.util.skill.ymlSkill;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.api.listeners.customEvents.attackEvents.AttackEvent;
import frostpyro.frostapi.util.skill.SkillManager;
import frostpyro.frostapi.util.skill.trigger.TriggerData;
import frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.SkillAction;
import frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.SkillArmorStand;
import frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.SkillEffect;
import frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.SkillSound;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Skill implements Listener{
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

    public void activateSkill() {
        if(data.getData() != null){
            if(FrostAPI.getPlugin().isDamagedBySkill(data.getData().getTarget())){
                FrostAPI.getPlugin().removeEntityDamageKey(data.getData().getTarget());
                return;
            }
        }
        if(configuration == null) return;
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

        new SkillAction(configuration, data).actionSection();
        new SkillSound(configuration, data).soundSection();
        new SkillEffect(configuration, data).effectSection();
        new SkillArmorStand(configuration, data).skillArmorSection();
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
