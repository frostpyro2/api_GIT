package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.interpret.base;

import frostpyro.frostapi.dataManage.stat.nonPlayer.EquipSlot;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;

import javax.swing.*;

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
        if(!(entity instanceof ArmorStand stand)) return;
        stand.setInvisible(isInvisible);
    }

    public void gravity(boolean isGravity){
        entity.setGravity(isGravity);
    }

    public void setAI(boolean isAI){
        if(!(entity instanceof LivingEntity livingEntity)) return;
        livingEntity.setAI(isAI);
    }

    enum Slot{
        HELMET,
        CHESTPLATE,
        LEGGINGS,
        BOOTS,
        MAINHAND,
        OFFHAND
    }

    public void setItemOnSlot(Slot slot, ItemStack stack){
        switch (slot){
            case HELMET -> setHelmet(stack);
            case CHESTPLATE -> setChestPlate(stack);
            case LEGGINGS -> setLeggings(stack);
            case BOOTS -> setBoots(stack);
            case MAINHAND -> setMainHand(stack);
            case OFFHAND -> setOffHand(stack);
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
}
