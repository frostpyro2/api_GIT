package frostpyro.frostapi.dataManage.stat.player;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.dataManage.data.DataManage;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;

public class YamlData implements DataManage {
    String[] strings = {"skillID", "level", "exp", "money"};

    @Override
    public @Nullable PlayerData getEntityData(LivingEntity livingEntity) {
        File playerFile = new File(FrostAPI.getPlugin().getDataFolder(), "player\\"+livingEntity.getName()+"_"+ livingEntity.getUniqueId().toString()+".yml");
        FileConfiguration configuration = new YamlConfiguration();
        try{
            configuration.load(playerFile);
        }
        catch (IOException | InvalidConfigurationException e){
            return null;
        }
        return new PlayerData(livingEntity.getUniqueId(), livingEntity.getName(), configuration.getInt("skillID"), configuration.getInt("level"), configuration.getDouble("exp"), configuration.getDouble("money"));
    }

    @Override
    public void createData(LivingEntity livingEntity){
       File file = new File(FrostAPI.getPlugin().getDataFolder(), "player\\" + livingEntity.getName() + "_" + livingEntity.getUniqueId() + ".yml");
       if(!file.getParentFile().exists()){
           file.getParentFile().mkdirs();
       }
       if(!file.exists()){
           try{
               file.createNewFile();
               YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
               for(String string : strings){
                   configuration.set(string, 0);
               }
               configuration.save(file);
           }
           catch (IOException e){
               e.printStackTrace();
           }
       }
    }
}
