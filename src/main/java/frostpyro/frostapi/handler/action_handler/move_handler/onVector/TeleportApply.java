package frostpyro.frostapi.handler.action_handler.move_handler.onVector;

import frostpyro.frostapi.handler.action_handler.TargetAction;
import org.bukkit.entity.LivingEntity;

public class TeleportApply extends TargetAction {
    private double teleport;
    private float angle;
    public TeleportApply() {
        super();
    }

    public TeleportApply(LivingEntity livingEntity, double teleport) {
        super(livingEntity);
        this.teleport = teleport;
    }

    @Override
    public void entityAction() {
        super.getLivingEntity().teleport(super.getLivingEntity().getLocation().add(super.getLivingEntity().getLocation().getDirection().multiply(teleport)));
    }
}
