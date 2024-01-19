package frostpyro.frostapi.handler.action_handler.move_handler.onVertical;

import frostpyro.frostapi.handler.action_handler.TargetAction;
import org.bukkit.entity.LivingEntity;

public class TeleportJumpApply extends TargetAction {
    private double teleport;
    public TeleportJumpApply() {
        super();
    }

    public TeleportJumpApply(LivingEntity livingEntity, double teleport) {
        super(livingEntity);
        this.teleport = teleport;
    }

    @Override
    public void entityAction() {
        super.getLivingEntity().teleport(super.getLivingEntity().getLocation().add(0, teleport, 0));
    }
}
