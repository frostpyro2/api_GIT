package frostpyro.frostapi.util.skill.container;

import frostpyro.frostapi.util.skill.casting.SkillItem;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

public class SkillItemContainer {
    private static Map<Integer, SkillItemContainer> idContainer = new HashMap<>();
    private ItemStack stack;

    public SkillItemContainer(ItemStack stack){
        this.stack = stack;
    }

    public ItemStack getStack(){
        return stack;
    }


    public static void register(int id, SkillItemContainer container){
        idContainer.put(id, container);
    }

    public static Map<Integer, SkillItemContainer> getIdContainer(){
        return idContainer;
    }

    public static SkillItemContainer getItemContainer(int id){
        return idContainer.get(id);
    }
}
