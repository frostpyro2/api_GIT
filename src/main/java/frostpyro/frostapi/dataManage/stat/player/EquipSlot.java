package frostpyro.frostapi.dataManage.stat.player;

import org.bukkit.inventory.EquipmentSlot;

public enum EquipSlot {
    ARMOR,
    MAIN_HAND,
    OFF_HAND,
    ACCESSORY,
    OTHER;

    public boolean isHand(){
        return this == MAIN_HAND || this == OFF_HAND;
    }

    public static EquipSlot fromBukkit(EquipmentSlot slot){
        switch (slot){
            case HAND -> {
                return MAIN_HAND;
            }
            case FEET, LEGS, HEAD, CHEST ->{
                return ARMOR;
            }
            case OFF_HAND -> {
                return OFF_HAND;
            }
            default -> {
                return OTHER;
            }
        }
    }
}
