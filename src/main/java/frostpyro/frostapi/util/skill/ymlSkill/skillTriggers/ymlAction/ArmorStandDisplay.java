package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction;

import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ArmorStandDisplay {
    private ArmorStand standTmp;
    private ItemStack stack;
    public ArmorStandDisplay(ArmorStand stand, ItemStack stack){

    }

    public void changeDisplay(int model){
        ItemMeta meta = stack.getItemMeta();
        meta.setCustomModelData(model);
        stack.setItemMeta(meta);
    }

    public ArmorStand getStandTmp(){
        return standTmp;
    }
}
