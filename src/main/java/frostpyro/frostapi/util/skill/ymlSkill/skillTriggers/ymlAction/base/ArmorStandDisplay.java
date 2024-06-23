package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.base;

import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ArmorStandDisplay {
    private ArmorStand stand;

    public ArmorStandDisplay(ArmorStand stand){
        this.stand = stand;
    }

    public void setItem(ItemLocation location, ItemStack stack){
        switch (location){
            case HEAD -> head(stack);
            case CHEST -> chest(stack);
            case LEGS -> legs(stack);
            case FEET -> feet(stack);
            case MAIN_HAND -> mainHand(stack);
            case OFF_HAND -> offHand(stack);
        }
    }

    private void head(ItemStack stack){
        stand.getEquipment().setHelmet(stack);
    }

    private void chest(ItemStack stack){
        stand.getEquipment().setChestplate(stack);
    }

    private void legs(ItemStack stack){
        stand.getEquipment().setLeggings(stack);
    }

    private void feet(ItemStack stack){
        stand.getEquipment().setBoots(stack);
    }

    private void mainHand(ItemStack stack){
        stand.getEquipment().setItemInMainHand(stack);
    }

    private void offHand(ItemStack stack){
        stand.getEquipment().setItemInOffHand(stack);
    }

    public enum ItemLocation{
        HEAD,
        CHEST,
        LEGS,
        FEET,
        MAIN_HAND,
        OFF_HAND
    }

    public static class ArmorStandInit {

    }
}
