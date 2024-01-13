package frostpyro.frostapi.skill.handler.entityAction;

import org.bukkit.entity.LivingEntity;

import javax.annotation.Nullable;

public abstract class TargetEntity {
    private LivingEntity livingEntity;

    public TargetEntity(){

    }

    public TargetEntity(LivingEntity livingEntity){
        this.livingEntity = livingEntity;
    }

    public abstract void actFunction();

    @Nullable
    public LivingEntity getLivingEntity(){
        return livingEntity;
    }
}
