package frostpyro.frostapi.dataManage.stat.player;

import frostpyro.frostapi.dataManage.stat.StatProvider;
import frostpyro.frostapi.util.skill.trigger.TriggerData;
import frostpyro.frostapi.util.skill.trigger.TriggerType;
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
        playerDataTmp.castSkill(data);
    }

    @Override
    public double getStat(String stat) {
        return playerDataTmp.getStat(stat);
    }

    public PlayerDataTmp tmp(){
        return playerDataTmp;
    }
}
