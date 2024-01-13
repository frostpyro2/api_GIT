package frostpyro.frostapi.skill.handler.action_handler;

import org.bukkit.entity.LivingEntity;

import javax.annotation.Nullable;

public abstract class TargetAction {
    private LivingEntity livingEntity;
    public TargetAction(){

    }

    public TargetAction(LivingEntity livingEntity){
        this.livingEntity = livingEntity;
    }

    public abstract void entityAction();

    public LivingEntity getLivingEntity(){
        return livingEntity;
    }
}

