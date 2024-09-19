package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.interpret.base;

import frostpyro.frostapi.FrostAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;

public class EntitySetting {
    private Entity entity;
    public EntitySetting(Entity entity){
        this.entity = entity;
    }

    public void marker(boolean isMarker){
        if(!(entity instanceof ArmorStand stand)) return;
        stand.setMarker(isMarker);
    }

    public void small(boolean isSmall){
        if(!(entity instanceof ArmorStand stand)) return;
        stand.setSmall(isSmall);
    }

    public void invisible(boolean isInvisible){
        if(!(entity instanceof LivingEntity living)){
            return;
        }
        living.setInvisible(isInvisible);
    }

    public void gravity(boolean isGravity){
        entity.setGravity(isGravity);
    }

    public void setAI(boolean isAI){
        if(!(entity instanceof LivingEntity livingEntity)) return;
        livingEntity.setAI(isAI);
    }

    public void setShooter(Entity entity){
        if(!(this.entity instanceof Projectile projectile)) return;
        if(!(entity instanceof ProjectileSource source)) return;
        projectile.setShooter(source);
    }

    public void setRotation(float pitch, float yaw){
        if(!(entity instanceof LivingEntity living)) return;
        living.setRotation(yaw, pitch);
    }

    public void setInvulnerable(boolean invulnerable){
        if(!(entity instanceof LivingEntity living)) return;
        living.setInvulnerable(invulnerable);
    }
    public void remove(){
        entity.remove();
    }

    public void setItemOnSlot(Slot slot, ItemStack stack){
        switch (slot){
            case HELMET -> setHelmet(stack);
            case CHESTPLATE -> setChestPlate(stack);
            case LEGGINGS -> setLeggings(stack);
            case BOOTS -> setBoots(stack);
            case MAIN_HAND -> setMainHand(stack);
            case OFF_HAND -> setOffHand(stack);
            default -> throw new IllegalArgumentException("invalid slot");
        }
    }



    private void setHelmet(ItemStack stack){
        if(!(entity instanceof LivingEntity livingEntity)) return;
        livingEntity.getEquipment().setHelmet(stack);
    }

    private void setChestPlate(ItemStack stack){
        if(!(entity instanceof LivingEntity livingEntity)) return;
        livingEntity.getEquipment().setChestplate(stack);
    }

    private void setLeggings(ItemStack stack){
        if(!(entity instanceof LivingEntity livingEntity)) return;
        livingEntity.getEquipment().setLeggings(stack);
    }

    private void setBoots(ItemStack stack){
        if(!(entity instanceof LivingEntity livingEntity)) return;
        livingEntity.getEquipment().setBoots(stack);
    }

    private void setMainHand(ItemStack stack){
        if(!(entity instanceof LivingEntity livingEntity)) return;
        livingEntity.getEquipment().setItemInMainHand(stack);
    }

    private void setOffHand(ItemStack stack){
        if(!(entity instanceof LivingEntity livingEntity)) return;
        livingEntity.getEquipment().setItemInOffHand(stack);
    }

    public static class ArmorStandSetting{
        private ArmorStand stand;
        public ArmorStandSetting(Entity entity){
            if(entity instanceof ArmorStand st){
                this.stand = st;
            }
        }

        public void pose(EulerAngle angle, Slot slot){
            if(stand == null) return;
            switch (slot){
                case HEAD -> headPose(angle);
                case LEFT_ARM -> leftArmPose(angle);
                case RIHGT_ARM -> rightArmPose(angle);
                case LEFT_LEG -> leftLegPose(angle);
                case RIGHT_LEG -> rightLegPose(angle);
                case BODY -> bodyPose(angle);
                default -> throw new IllegalArgumentException("invalid slot");
            }
        }

        private void headPose(EulerAngle angle){
            stand.setHeadPose(angle);
        }

        private void leftArmPose(EulerAngle angle){
            stand.setLeftArmPose(angle);
        }

        private void rightArmPose(EulerAngle angle){
            stand.setRightArmPose(angle);
        }

        private void leftLegPose(EulerAngle angle){
            stand.setLeftLegPose(angle);
        }

        private void rightLegPose(EulerAngle angle){
            stand.setRightLegPose(angle);
        }

        private void bodyPose(EulerAngle angle){
            stand.setBodyPose(angle);
        }
    }
}
