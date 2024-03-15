package frostpyro.frostapi.dataManage.stat;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.dataManage.stat.data.StatMap;
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
    static StatProvider get(LivingEntity livingEntity){
        File playerFile = new File(FrostAPI.getPlugin().getDataFolder(), "player\\"+livingEntity.getName()+"_"+ livingEntity.getUniqueId().toString()+".yml");
        FileConfiguration configuration = new YamlConfiguration();
        try{
            configuration.load(playerFile);
        }
        catch (IOException | InvalidConfigurationException e){
            return null;
        }
        if(livingEntity instanceof Player){
            return new PlayerDataTmp(livingEntity.getUniqueId(), livingEntity.getName(), configuration.getInt("skillID"), configuration.getInt("level"), configuration.getDouble("exp"), configuration.getDouble("money"));
        }
        return null;
    }

    static StatProvider get(LivingEntity livingEntity, EquipmentSlot slot, boolean cache){
        final StatMap statMap = PlayerDataTmp.get((Player) livingEntity).statMap();

        return cache ? statMap.cache(slot) : statMap;
    }

    static StatProvider get(UUID uuid){
        Entity entity = Bukkit.getEntity(uuid);
        if(entity == null) return null;
        return get((LivingEntity) entity);
    }
}
