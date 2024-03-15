package frostpyro.frostapi.dataManage.stat;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.dataManage.stat.player.PlayerData;
import frostpyro.frostapi.util.skill.trigger.TriggerType;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class StatProvider {
    private UUID uuid;
    private String name;
    private int skillID;
    private double exp;
    private int level;

    public StatProvider(UUID uuid, String name, int skillID, double exp, int level){
        this.uuid = uuid;
        this.name = name;
        this.skillID = skillID;
        this.exp = exp;
        this.level = level;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSkillID() {
        return skillID;
    }

    public void setSkillID(int skillID) {
        this.skillID = skillID;
    }

    public double getExp() {
        return exp;
    }

    public void setExp(double exp) {
        this.exp = exp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Entity getEntity(){
        return Bukkit.getEntity(uuid);
    }

    public void castSkill(TriggerType type){
        if(skillID == 0) return;
    }

    public static StatProvider get(LivingEntity livingEntity){
        File playerFile = new File(FrostAPI.getPlugin().getDataFolder(), "player\\"+livingEntity.getName()+"_"+ livingEntity.getUniqueId().toString()+".yml");
        FileConfiguration configuration = new YamlConfiguration();
        try{
            configuration.load(playerFile);
        }
        catch (IOException | InvalidConfigurationException e){
            return null;
        }
        if(livingEntity instanceof Player){
            return new PlayerData(livingEntity.getUniqueId(), livingEntity.getName(), configuration.getInt("skillID"), configuration.getInt("level"), configuration.getDouble("exp"), configuration.getDouble("money"));
        }
        return new StatProvider(livingEntity.getUniqueId(), livingEntity.getName(), configuration.getInt("skillID"), configuration.getDouble("exp"), configuration.getInt("level"));
    }

    public static StatProvider get(UUID uuid){
        Entity entity = Bukkit.getEntity(uuid);
        if(entity == null) return null;
        return get((LivingEntity) entity);
    }
}
