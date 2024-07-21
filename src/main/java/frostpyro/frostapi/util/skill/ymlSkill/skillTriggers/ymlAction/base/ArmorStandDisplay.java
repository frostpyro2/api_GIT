package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.base;

import frostpyro.frostapi.util.skill.trigger.PlayerTriggerData;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;

import java.util.Collection;
import java.util.List;

public class ArmorStandDisplay {
    private ArmorStand stand;
    private PlayerTriggerData data;
    public ArmorStandDisplay(PlayerTriggerData data){
        this.data = data;
    }

    public void spawnStand(){
        stand = data.getCast().getEntity().getWorld().spawn(data.getSource(), ArmorStand.class);
    }
    public ArmorStand getStand(){
        return stand;
    }
    public void setStand(Entity entity){
        if(!(entity instanceof ArmorStand std)) return;
        this.stand = std;
    }
    public PlayerTriggerData dependency(){
        return this.data;
    }

    public void setItem(ItemLocation location, ItemStack stack) {
        switch (location) {
            case HEAD -> head(stack);
            case CHEST -> chest(stack);
            case LEGS -> legs(stack);
            case FEET -> feet(stack);
            case MAIN_HAND -> mainHand(stack);
            case OFF_HAND -> offHand(stack);
        }
    }

    private void head(ItemStack stack){
        if(stand.getEquipment() == null) return;
        stand.getEquipment().setHelmet(stack);
    }

    private void chest(ItemStack stack){
        if(stand.getEquipment() == null) return;
        stand.getEquipment().setChestplate(stack);
    }

    private void legs(ItemStack stack){
        if(stand.getEquipment() == null) return;
        stand.getEquipment().setLeggings(stack);
    }

    private void feet(ItemStack stack){
        if(stand.getEquipment() == null) return;
        stand.getEquipment().setBoots(stack);
    }

    private void mainHand(ItemStack stack){
        if(stand.getEquipment() == null) return;
        stand.getEquipment().setItemInMainHand(stack);
    }

    private void offHand(ItemStack stack){
        if(stand.getEquipment() == null) return;
        stand.getEquipment().setItemInOffHand(stack);
    }

    public void setPose(EulerAngle angle, PoseLocation poseLocation){
        switch (poseLocation){
            case HEAD -> head(angle);
            case BODY -> body(angle);
            case LEFT_ARM -> left_arm(angle);
            case RIGHT_ARM -> right_arm(angle);
            case LEFT_LEG -> left_leg(angle);
            case RIGHT_LEG -> right_leg(angle);
        }
    }

    private void head(EulerAngle angle){
        stand.setHeadPose(angle);
    }

    private void body(EulerAngle angle){
        stand.setBodyPose(angle);
    }

    private void left_arm(EulerAngle angle){
        stand.setLeftArmPose(angle);
    }

    private void right_arm(EulerAngle angle){
        stand.setRightArmPose(angle);
    }

    private void left_leg(EulerAngle angle){
        stand.setLeftLegPose(angle);
    }

    private void right_leg(EulerAngle angle){
        stand.setRightLegPose(angle);
    }


    public enum ItemLocation{
        HEAD,
        CHEST,
        LEGS,
        FEET,
        MAIN_HAND,
        OFF_HAND
    }

    public enum PoseLocation{
        HEAD,
        LEFT_ARM,
        RIGHT_ARM,
        LEFT_LEG,
        RIGHT_LEG,
        BODY
    }

    public static class ArmorStandInit {
        private ArmorStand stand;
        public ArmorStandInit(ArmorStand stand){
            this.stand = stand;
        }

        public void isGravity(Boolean isGravity){
            if(isGravity == null){
                return;
            }
            stand.setGravity(isGravity);
        }

        public void isMarker(Boolean isMarker){
            if(isMarker == null){
                return;
            }
            stand.setMarker(isMarker);
        }

        public void isInvisible(Boolean isInvisible){
            if(isInvisible == null){
                return;
            }
            stand.setInvisible(isInvisible);
        }

        public void isSmall(Boolean isSmall){
            if(isSmall == null){
                return;
            }
            stand.setSmall(isSmall);
        }

        public void hasPlate(Boolean hasPlate){
            if(hasPlate == null){
                return;
            }
            stand.setBasePlate(hasPlate);
        }

        public void remove(){
            this.stand.remove();
        }

        public void setLocation(Location location){
            stand.teleport(location);
        }
    }
}
