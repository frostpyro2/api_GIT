package frostpyro.frostapi.dataManage.stat.data;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.dataManage.stat.player.PlayerDataTmp;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

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
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);

        try{
            for(String string : StatFile.getStatType()){
                if(configuration.contains(string)) continue;
                configuration.set("stats."+string, FrostAPI.getPlugin().stats.getConfigurationSection(string).getDouble("val"));
            }
            if(!configuration.contains("skill")){
                configuration.set("skill", null);
            }
            configuration.save(file);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static FileConfiguration getFile(LivingEntity entity){
        String name = entity.getName();
        UUID uuid = entity.getUniqueId();

        File file = new File(FrostAPI.getPlugin().getDataFolder(), "player\\" + name + "_" + uuid + ".yml");

        if(!file.exists())
            generate(entity);

        FileConfiguration configuration = new YamlConfiguration();

        try{
            configuration.load(file);
        }
        catch (IOException | InvalidConfigurationException e){
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "yml went wrong");
            return new YamlConfiguration();
        }

        return configuration;
    }
}
