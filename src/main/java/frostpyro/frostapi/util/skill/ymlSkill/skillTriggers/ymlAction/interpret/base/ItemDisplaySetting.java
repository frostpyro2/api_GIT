package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.interpret.base;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemDisplaySetting {
    private ItemStack stack;

    public ItemDisplaySetting(ItemStack stack){
        this.stack = stack;
    }

    public ItemStack getItemStack(){
        return stack;
    }

    public void setModel(int model){
        ItemMeta meta = stack.getItemMeta();
        meta.setCustomModelData(model);
        stack.setItemMeta(meta);
    }
}
