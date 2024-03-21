package frostpyro.frostapi.util.skill.skillItem;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.util.skill.skillManager.SkillManager;
import frostpyro.frostapi.util.skill.trigger.TriggerData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class SkillItem extends SkillManager {

    private static List<ItemStack> skillItems = new ArrayList<>();

    public SkillItem(TriggerData data) {
        super(data);
    }

    @Override
    public void cast() {

    }

    public static void registerItem()  {
        File file = new File(FrostAPI.getPlugin().getDataFolder(), "\\skill\\item");
        File[] ymlFiles = file.listFiles();
        if(ymlFiles == null) return;
        for(File ymlFile : ymlFiles){
            FileConfiguration configuration = new YamlConfiguration();
            try {
                configuration.load(ymlFile);
            }
            catch (IOException|InvalidConfigurationException e){
                e.printStackTrace();
                break;
            }
            ItemStack itemStack;
            try{
                itemStack = new ItemStack(Material.getMaterial(configuration.getString("item")));
            }catch (Exception any){
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "item type's name should be \"item\"");
                break;
            }
            ItemMeta meta = itemStack.getItemMeta();
            if(meta == null) break;
            try{
                meta.setCustomModelData(configuration.getInt("model"));
            }catch (Exception any){
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "item model's name should be \"model\"");
                break;
            }
            try{
                List<String> nameElemental = configuration.getStringList("name");

                if(!nameElemental.isEmpty()){
                    int index = 1;

                    String string = "";

                    for(String name : nameElemental){
                        if(index % 2 == 0){
                            string += name;
                        }
                        else{
                            if(ChatColor.valueOf(name).isColor()){
                                string += ChatColor.valueOf(name);
                            }
                        }
                        index ++;
                    }
                    meta.setDisplayName(string);
                }
            }catch (Exception any){
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "item name's name should be \"name\", or inner elemental should be LIST");
                break;
            }
            try{
                meta.setLore(configuration.getStringList("lore"));
            }catch (Exception any){
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "item lore's name should be \"lore\"");
                break;
            }
            skillItems.add(itemStack);
            Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GREEN + "item added! :" + itemStack.getType());
        }
    }
}
