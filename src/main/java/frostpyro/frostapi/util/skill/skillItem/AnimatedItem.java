package frostpyro.frostapi.util.skill.skillItem;

import frostpyro.frostapi.FrostAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.entity.LingeringPotionSplashEvent;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnimatedItem {
    private static Map<String, ItemStack> animatedItems = new HashMap<>();
    private static Map<String, FileConfiguration> listConfig = new HashMap<>();

    public ItemStack stack(String dir){
        return animatedItems.get(dir);
    }

    public List<Integer> getModelData(String dir){
        final List<Integer> intList = new ArrayList<>();
        FileConfiguration configuration = listConfig.get(dir);
        List<Map<?, ?>> mapList = configuration.getMapList("custom");
        for(Map<?, ?> map : mapList){
            try{
                intList.add((int) map.get("model"));
            }catch (Exception any){
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + dir + ": model call failed!");
                return new ArrayList<>();
            }
        }
        return intList;
    }

    public List<Long> getDelay(String dir){
        final List<Long> longList = new ArrayList<>();
        longList.add(0L);
        FileConfiguration configuration = listConfig.get(dir);
        List<Map<?, ?>> mapList = configuration.getMapList("custom");
        for (Map<?, ?> map : mapList){
            try{
                longList.add((long) map.get("delay"));
            }
            catch (Exception any){
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + dir + ": delay call failed!");
                return new ArrayList<>();
            }
        }
        return longList;
    }



    public static void registerItemAnimation(){
        File file = new File(FrostAPI.getPlugin().getDataFolder(), "skill\\effectItem");
        File[] files = file.listFiles();
        if(files == null) return;

        for(File effectFile : files){
            String[] strings = effectFile.getName().split("\\.");
            FileConfiguration configuration = new YamlConfiguration();
            try{
                configuration.load(effectFile);
            }
            catch (Exception any){
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "error while loading effect" + effectFile.getName());
                continue;
            }
            ItemStack stack;
            try {
                stack = new ItemStack(Material.getMaterial(configuration.getString("item")));
            }
            catch (Exception any){
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "failed to load item");
                continue;
            }
            animatedItems.put(strings[0], stack);
            listConfig.put(strings[0], configuration);
            Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_PURPLE + "animation added:" + strings[0]);
        }
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "--------------------------------");
    }
}
