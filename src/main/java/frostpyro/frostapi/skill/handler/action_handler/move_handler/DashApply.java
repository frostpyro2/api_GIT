package frostpyro.frostapi.skill.handler.action_handler.move_handler;

import frostpyro.frostapi.skill.handler.action_handler.TargetAction;
import org.bukkit.entity.LivingEntity;

public class DashApply extends TargetAction {
    private double speed;
    public DashApply() {
        super();
    }

    public DashApply(LivingEntity livingEntity, double speed) {
        super(livingEntity);
        this.speed = speed;
    }

    @Override
    public void entityAction() {
        super.getLivingEntity().setVelocity(super.getLivingEntity().getLocation().getDirection().multiply(speed));
    }
}
