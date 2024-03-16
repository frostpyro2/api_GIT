package frostpyro.frostapi.dataManage.stat.modifier;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.dataManage.stat.data.PlayerFile;
import frostpyro.frostapi.dataManage.stat.player.PlayerDataTmp;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;

public class Modifier {
    private final PlayerDataTmp player;
    public Modifier(PlayerDataTmp player){
        this.player = player;
    }
    public void modifyStat(double point, String string){
        File file = new File(FrostAPI.getPlugin().getDataFolder(), "player\\" + player.getEntity().getName() + "_" + player.getEntity().getUniqueId() + ".yml");
        if(!file.exists()) return;
        FileConfiguration configuration = PlayerFile.getFile(player.getEntity());
        if(configuration == null) return;
        try{
            configuration.load(file);
            configuration.set(string, point);
            configuration.save(file);
        }catch (InvalidConfigurationException| IOException e){
            e.printStackTrace();
        }
    }

}
