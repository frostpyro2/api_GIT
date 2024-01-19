package frostpyro.frostapi.handler.entityAction;

import org.bukkit.entity.LivingEntity;

public abstract class TargetEntity {
    private LivingEntity livingEntity;

    public TargetEntity(){

    }

    public TargetEntity(LivingEntity livingEntity){
        this.livingEntity = livingEntity;
    }

    public abstract void actFunction();

    public LivingEntity getLivingEntity(){
        return livingEntity;
    }
}
