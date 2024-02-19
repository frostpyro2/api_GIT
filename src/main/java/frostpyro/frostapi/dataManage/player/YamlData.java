package frostpyro.frostapi.dataManage.player;

import frostpyro.frostapi.FrostAPI;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class YamlData implements DataManage{
    String[] strings = {"skillID", "level", "exp", "money"};

    @Override
    public @Nullable PlayerData getPlayerData(Player player) {
        File playerFile = new File(FrostAPI.getPlugin().getDataFolder(), "player\\"+player.getName()+"_"+ player.getUniqueId().toString()+".yml");
        FileConfiguration configuration = new YamlConfiguration();
        try{
            configuration.load(playerFile);
        }
        catch (IOException | InvalidConfigurationException e){
            return null;
        }
        return new PlayerData(player.getUniqueId().toString(), player.getName(), configuration.getInt("skillID"), configuration.getInt("level"), configuration.getDouble("exp"), configuration.getDouble("money"));
    }

    @Override
    public void createData(Player player){
       File file = new File(FrostAPI.getPlugin().getDataFolder(), "player\\" + player.getName() + "_" + player.getUniqueId() + ".yml");
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
