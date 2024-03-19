package frostpyro.frostapi.dataManage.stat.data;

import frostpyro.frostapi.dataManage.stat.StatProvider;
import frostpyro.frostapi.dataManage.stat.player.EquipSlot;
import frostpyro.frostapi.dataManage.stat.player.PlayerData;
import frostpyro.frostapi.dataManage.stat.player.PlayerDataTmp;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EquipmentSlot;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StatMap implements StatProvider {
    private final PlayerDataTmp data;
    private final Map<String, StatInstance> stats = new ConcurrentHashMap<>();
    public StatMap(PlayerDataTmp data){
        this.data = data;
    }
    @Override
    public LivingEntity getEntity() {
        return data.getEntity();
    }

    @Override
    public double getStat(String stat) {
        return getInstance(stat).getFinal();
    }

    @NotNull
    public StatInstance getInstance(String string){
        return stats.computeIfAbsent(string, statID -> new StatInstance(this, statID));
    }

    public Collection<StatInstance> getInstances(){
        return stats.values();
    }

    public PlayerData cache(EquipSlot slot){
        return new PlayerData(data, slot);
    }
}
