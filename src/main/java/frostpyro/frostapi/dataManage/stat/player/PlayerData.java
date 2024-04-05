package frostpyro.frostapi.dataManage.stat.player;

import frostpyro.frostapi.dataManage.stat.StatProvider;
import frostpyro.frostapi.util.skill.trigger.TriggerData;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

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
}
