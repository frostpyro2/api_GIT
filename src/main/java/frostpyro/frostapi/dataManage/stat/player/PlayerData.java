package frostpyro.frostapi.dataManage.stat.player;

import frostpyro.frostapi.dataManage.stat.StatProvider;
import frostpyro.frostapi.util.skill.trigger.TriggerData;
import frostpyro.frostapi.util.skill.trigger.TriggerType;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.Map;

public class PlayerData implements StatProvider {
    private PlayerDataTmp playerDataTmp;
    private EquipSlot slot;

    private Player player;
    public PlayerData(PlayerDataTmp playerDataTmp, EquipSlot slot){
        player = (Player) playerDataTmp.getEntity();
        this.playerDataTmp = playerDataTmp;
        this.slot = slot;
    }

    @Override
    public LivingEntity getEntity() {
        return player;
    }

    public void castSkill(TriggerData data){
        if(slot == null)
            playerDataTmp.castSkill(data);
        else
            playerDataTmp.castSkill(data, slot);
    }

    @Override
    public double getStat(String stat) {
        return playerDataTmp.getStat(stat);
    }

    public void removeCoolDown(Configuration config){
        playerDataTmp.removeCoolDown(config);
    }

    public PlayerDataTmp tmp(){
        return playerDataTmp;
    }

    public void setCoolDown(Configuration config, double sec){
        playerDataTmp.setCoolDown(config, sec);
    }

    public boolean isCoolDown(Configuration config){
        return playerDataTmp.isCoolDown(config);
    }

    public void setToggle(FileConfiguration configuration){
        playerDataTmp.setToggle(configuration);
    }

    public FileConfiguration getToggle(){
        return playerDataTmp.getToggle();
    }

    public void registerToggle(FileConfiguration configuration, Map<String, FileConfiguration> tmp){
        playerDataTmp.registerToggle(configuration, tmp);
    }

    public Map<FileConfiguration, Map<String, FileConfiguration>> getToggleMap(){
        return playerDataTmp.getToggleMap();
    }

    public void removeToggle(FileConfiguration configuration){
        playerDataTmp.removeToggle(configuration);
    }

    public boolean notDuration(Configuration configuration){
        return playerDataTmp.notDuration(configuration);
    }

    public void setDuration(Configuration configuration, double duration){
        playerDataTmp.setDuration(configuration, duration);
    }

    public void clearToggleCache(){
        playerDataTmp.clearToggleCache();
    }

    public void removeDuration(Configuration configuration){
        playerDataTmp.removeDuration(configuration);
    }

    public void addSuppress(FileConfiguration data){
        playerDataTmp.addSuppress(data);
    }

    public void removeSuppress(FileConfiguration data){
        playerDataTmp.removeSuppress(data);
    }

    public boolean isSuppressed(FileConfiguration data){
        return playerDataTmp.isSuppressed(data);
    }
}
