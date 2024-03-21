package frostpyro.frostapi.util.skill.skillItem;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.util.skill.skillManager.SkillManager;
import frostpyro.frostapi.util.skill.trigger.TriggerData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class SkillItem extends SkillManager {

    private static final List<ItemStack> skillItems = new ArrayList<>();
    private static final Map<ItemStack, Map<String, String>> skillActivate = new HashMap<>();

    public SkillItem(TriggerData data) {
        super(data);
    }

    @Override
    public void cast() {
        TriggerData data = data();
        Player player = (Player) data.getCast().getEntity();
        if(!skillItems.contains(player.getInventory().getItemInMainHand())) return;
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
                continue;
            }
            ItemStack itemStack;
            try{
                itemStack = new ItemStack(Material.getMaterial(configuration.getString("item")));
            }catch (Exception any){
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "item type's name should be \"item\"");
                continue;
            }
            ItemMeta meta = itemStack.getItemMeta();
            if(meta == null) continue;
            try{
                meta.setCustomModelData(configuration.getInt("model"));
            }catch (Exception any){
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "item model's name should be \"model\"");
                continue;
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
                continue;
            }
            try{
                meta.setLore(configuration.getStringList("lore"));
            }catch (Exception any){
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "item lore's name should be \"lore\"");
                continue;
            }
            skillItems.add(itemStack);
            ConfigurationSection skillSection = configuration.getConfigurationSection("skill");
            if(skillSection != null){
                Map<String, String> temp = new HashMap<>();
                for(String keys : skillSection.getKeys(false)){
                    temp.put(keys, skillSection.getString(keys));
                }
                skillActivate.put(itemStack, temp);
            }
            Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GREEN + "item added! :" + itemStack.getType());
        }
    }
}
