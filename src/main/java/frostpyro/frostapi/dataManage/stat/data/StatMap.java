package frostpyro.frostapi.dataManage.stat.data;

import frostpyro.frostapi.dataManage.stat.StatProvider;
import frostpyro.frostapi.dataManage.stat.player.PlayerData;
import frostpyro.frostapi.dataManage.stat.player.PlayerDataTmp;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EquipmentSlot;

public class StatMap implements StatProvider {
    private PlayerDataTmp data;
    public StatMap(PlayerDataTmp data){
        this.data = data;
    }
    @Override
    public LivingEntity getEntity() {
        return null;
    }

    @Override
    public double getStat(String stat) {
        return 0;
    }

    public StatProvider cache(EquipmentSlot slot){
        return new PlayerData(data, slot);
    }
}
