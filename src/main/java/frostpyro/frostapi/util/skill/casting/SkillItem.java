package frostpyro.frostapi.util.skill.casting;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.dataManage.stat.data.PlayerFile;
import frostpyro.frostapi.util.lib.Utility;
import frostpyro.frostapi.util.skill.SkillManager;
import frostpyro.frostapi.util.skill.container.SkillItemContainer;
import frostpyro.frostapi.util.skill.trigger.PlayerTriggerData;
import frostpyro.frostapi.util.skill.ymlSkill.run.PlayerSkill;
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

public class SkillItem extends SkillManager {

    private static final List<ItemStack> skillItems = new ArrayList<>();
    private static final Map<ItemStack, Map<String, List<String>>> skillActivate = new HashMap<>();
    private static final Map<Integer, SkillItemContainer> skillItem = new HashMap<>();

    public SkillItem(PlayerTriggerData data) {
        super(data);
    }

    @Override
    public void cast() {
        PlayerTriggerData data = data();
        Player player = (Player) data.getTmp().getEntity();
        FileConfiguration file = PlayerFile.getFile(player);
        ConfigurationSection section = file.getConfigurationSection("skill");
        if(section == null){
            player.sendMessage("no skill section!");
            return;
        }
        List<?> skills = section.getList(data.getType().getType());
        if(skills == null) return;
        for(Object skill : skills){
            if(skill instanceof String str)
                new PlayerSkill(str, data).activateSkill();
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
                    temp.put(keys, skillSection.getStringList(keys));
                }
                skillActivate.put(itemStack, temp);
                try{
                    if(configuration.getInt("id") < 0) throw new Exception();
                    SkillItemContainer.register(configuration.getInt("id"), new SkillItemContainer(itemStack));
                }catch (Exception e){
                    SkillItemContainer.register(-1, new SkillItemContainer(itemStack));
                }
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
