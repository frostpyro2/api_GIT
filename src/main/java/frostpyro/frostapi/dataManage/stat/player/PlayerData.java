package frostpyro.frostapi.dataManage.stat.player;

import frostpyro.frostapi.dataManage.stat.StatProvider;
import frostpyro.frostapi.dataManage.stat.data.StatMap;
import frostpyro.frostapi.util.skill.trigger.TriggerType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;

public class PlayerData implements StatProvider {
    private PlayerDataTmp playerDataTmp;
    private EquipmentSlot slot;

    private Player player;
    public PlayerData(PlayerDataTmp playerDataTmp, EquipmentSlot slot){
        player = (Player) playerDataTmp.getEntity();
        this.playerDataTmp = playerDataTmp;
        this.slot = slot;
    }

    @Override
    public LivingEntity getEntity() {
        return player;
    }

    public void castSkill(TriggerType type){
        playerDataTmp.castSkill(type);
    }

    @Override
    public double getStat(String stat) {
        return playerDataTmp.getStat(stat);
    }

    public PlayerDataTmp tmp(){
        return playerDataTmp;
    }
}
