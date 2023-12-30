package frostpyro.frostapi.players;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.skill.Skill;
import frostpyro.frostapi.skill.trigger.TriggerType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class PlayerData {
    private String uuid;
    private String name;
    private int skillID;
    private int level;
    private double exp;
    private double money;

    public PlayerData(String uuid, String name, int skillID, int level, double exp, double money){
        this.uuid = uuid;
        this.name = name;
        this.skillID = skillID;
        this.level = level;
        this.exp = exp;
        this.money = money;
    }

    public void setSkillID(int id){
        this.skillID = id;
    }

    public int getSkillID(){
        return this.skillID;
    }

    public Player getPlayer(){
        return Bukkit.getPlayer(UUID.fromString(this.uuid));
    }

    public void castSkill(TriggerType trigger){
        List<String> ymlList = FrostAPI.getPlugin().skillName();
        ConfigurationSection section = null;
        Skill skill;
        for(String yml : Objects.requireNonNull(ymlList)){
            String ymlFile = yml + ".yml";
            File file = new File(FrostAPI.getPlugin().getDataFolder(), "\\skill\\"+ymlFile);
            FileConfiguration configuration = new YamlConfiguration();
            try{
                configuration.load(file);
            }
            catch (IOException | InvalidConfigurationException e){
                e.printStackTrace();
                return;
            }
            ConfigurationSection testSection = configuration.getConfigurationSection(yml);
            if(testSection == null){
                Bukkit.getLogger().info("SET THE FIRST LINE OF THE FILE:"+yml);
                return;
            }

            if(testSection.getInt("SKILL_ID") == this.getSkillID() && testSection.getStringList("TRIGGER_TYPE").contains(trigger.getType())){
                section = testSection;
                break;
            }
        }
        if(section == null){
            Bukkit.getLogger().info(ChatColor.RED+ "FUCK YOU");
            return;
        }
        skill = new Skill(section, trigger, this);
        skill.skillActivate();
    }
}
