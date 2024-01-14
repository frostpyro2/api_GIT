package frostpyro.frostapi.skill.handler.action_handler.move_handler.onVector;

import frostpyro.frostapi.skill.handler.action_handler.TargetAction;
import org.bukkit.entity.LivingEntity;

public class DashApply extends TargetAction {
    private double speed;
    private float angle;
    public DashApply() {
        super();
    }

    public DashApply(LivingEntity livingEntity, double speed, float angle) {
        super(livingEntity);
        this.speed = speed;
        this.angle = angle;
    }

    @Override
    public void entityAction() {
        super.getLivingEntity().setVelocity(super.getLivingEntity().getLocation().getDirection().multiply(speed));
    }
}
