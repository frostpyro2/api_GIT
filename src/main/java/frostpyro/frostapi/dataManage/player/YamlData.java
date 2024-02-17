package frostpyro.frostapi.dataManage.player;

import frostpyro.frostapi.FrostAPI;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;

public class YamlData implements DataManage{
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
}
