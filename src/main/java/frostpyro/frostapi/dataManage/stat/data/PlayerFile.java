package frostpyro.frostapi.dataManage.stat.data;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.dataManage.stat.player.PlayerDataTmp;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

public class PlayerFile {
    public static void generate(LivingEntity entity){
        String name = entity.getName();
        UUID uuid = entity.getUniqueId();

        File file = new File(FrostAPI.getPlugin().getDataFolder(), "player\\" + name + "_" + uuid + ".yml");
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        if(!file.exists()){
            try{
                file.createNewFile();
                YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);

                configuration.save(file);
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public static PlayerDataTmp getYmlData(LivingEntity entity){
        String name = entity.getName();
        UUID uuid = entity.getUniqueId();

        File file = new File(FrostAPI.getPlugin().getDataFolder(), "player\\" + name + "_" + uuid + ".yml");

        if(!file.exists())
            return new PlayerDataTmp((Player) entity);

        FileConfiguration configuration = new YamlConfiguration();

        try{
            configuration.load(file);
        }
        catch (IOException | InvalidConfigurationException e){
            return new PlayerDataTmp((Player) entity);
        }

        return null;
    }

    public static FileConfiguration getFile(LivingEntity entity){
        String name = entity.getName();
        UUID uuid = entity.getUniqueId();

        File file = new File(FrostAPI.getPlugin().getDataFolder(), "player\\" + name + "_" + uuid + ".yml");

        if(!file.exists())
            return null;

        FileConfiguration configuration = new YamlConfiguration();

        try{
            configuration.load(file);
        }
        catch (IOException | InvalidConfigurationException e){
            return null;
        }

        return configuration;
    }
}
