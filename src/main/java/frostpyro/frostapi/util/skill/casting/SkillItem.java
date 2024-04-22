package frostpyro.frostapi.util.skill.casting;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.util.skill.SkillManager;
import frostpyro.frostapi.util.skill.trigger.TriggerData;
import frostpyro.frostapi.util.skill.ymlSkill.Skill;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.w3c.dom.Attr;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class SkillItem extends SkillManager {

    private static final List<ItemStack> skillItems = new ArrayList<>();
    private static final Map<ItemStack, Map<String, List<String>>> skillActivate = new HashMap<>();

    public SkillItem(TriggerData data) {
        super(data);
    }

    @Override
    public void cast() {
        TriggerData data = data();
        Player player = (Player) data.getTmp().getEntity();
        if(!skillItems.contains(player.getInventory().getItemInMainHand())) return;
        Map<String, List<String>> getSkill = skillActivate.get(player.getInventory().getItemInMainHand());
        List<String> files = getSkill.computeIfAbsent(data.getType().getType(), NONE -> null);
        if(files == null) return;
        for(String file: files){
            Skill skill = new Skill(file, data);
            skill.activateSkill();
        }
    }

    public static void registerItem()  {
        File file = new File(FrostAPI.getPlugin().getDataFolder(), "\\skill\\item");
        File[] ymlFiles = file.listFiles();
        if(ymlFiles == null){
            Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "item file does not exist!");
            return;
        }
        if(ymlFiles.length == 0){
            Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "item file is empty!");
            return;
        }
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
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "item type's name should be \"item\"\nERROR ON:" + ymlFile.getName());
                continue;
            }
            ItemMeta meta = itemStack.getItemMeta();
            if(meta == null) continue;
            try{
                meta.setCustomModelData(configuration.getInt("model"));
            }catch (Exception any){
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "item model's name should be \"model\"\nERROR ON:" + ymlFile.getName());
                continue;
            }
            try{
                List<String> nameElemental = configuration.getStringList("name");

                if(!nameElemental.isEmpty()){
                    int index = 1;

                    StringBuilder string = new StringBuilder();

                    for(String name : nameElemental){
                        if(index % 2 == 0){
                            string.append(name);
                        }
                        else{
                            if(ChatColor.valueOf(name).isColor()){
                                string.append(ChatColor.valueOf(name));
                            }
                        }
                        index ++;
                    }
                    meta.setDisplayName(string.toString());
                }
            }catch (Exception any){
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "item name's name should be \"name\", or inner elemental should be LIST\nERROR ON:" + ymlFile.getName());
                continue;
            }
            try{
                meta.setLore(configuration.getStringList("lore"));
            }catch (Exception any){
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "item lore's name should be \"lore\"\nERROR ON:" + ymlFile.getName());
                continue;
            }
            try{
                meta.setUnbreakable(configuration.getBoolean("unbreakable"));
            }catch (Exception any){
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "item durable's name should be \"unbreakable\"\nERROR ON:" + ymlFile.getName());
                continue;
            }
            itemStack.setItemMeta(meta);
            skillItems.add(itemStack);
            ConfigurationSection skillSection = configuration.getConfigurationSection("skill");
            if(skillSection != null){
                Map<String, List<String>> temp = new HashMap<>();
                for(String keys : skillSection.getKeys(false)){
                    List<String> skillList = new ArrayList<>();

                    for(String eachSkill : skillSection.getStringList(keys)){
                        if(!getSkills().contains(eachSkill)) continue;
                        skillList.add(eachSkill);
                    }
                    temp.put(keys, skillList);
                }
                skillActivate.put(itemStack, temp);
            }
            String[] strings = ymlFile.getName().split("\\.");
            Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GREEN + "item added:" + strings[0]);
        }
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "--------------------------------");
    }

    public static List<ItemStack> skillItemList(){
        return skillItems;
    }
}
