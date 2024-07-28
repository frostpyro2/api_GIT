package frostpyro.frostapi.dataManage.stat;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.dataManage.stat.data.StatMap;
import frostpyro.frostapi.dataManage.stat.nonPlayer.EntityDataTmp;
import frostpyro.frostapi.dataManage.stat.player.EquipSlot;
import frostpyro.frostapi.dataManage.stat.player.PlayerData;
import frostpyro.frostapi.dataManage.stat.player.PlayerDataTmp;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public interface StatProvider {
    LivingEntity getEntity();
    double getStat(String stat);

    static StatProvider get(LivingEntity livingEntity, EquipSlot slot, boolean cache){
        if(PlayerDataTmp.get(livingEntity.getUniqueId()) == null) return null;
        final StatMap statMap = PlayerDataTmp.get(livingEntity.getUniqueId()).statMap();

        return cache ? statMap.cache(slot) : statMap;
    }

    static StatProvider get(UUID uuid, EquipSlot slot, boolean cache){
        Entity entity = Bukkit.getEntity(uuid);
        if(entity == null) return null;
        return get((LivingEntity) entity, slot, cache);
    }

    static StatProvider getNonPlayer(String dataKey, frostpyro.frostapi.dataManage.stat.nonPlayer.EquipSlot slot, boolean cache){
         return null;
    }
}
