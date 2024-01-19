package frostpyro.frostapi.handler.action_handler.move_handler.onVertical;

import frostpyro.frostapi.handler.action_handler.TargetAction;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

public class JumpApply extends TargetAction {
    private double jump;
    public JumpApply() {
        super();
    }

    public JumpApply(LivingEntity livingEntity, double jump) {
        super(livingEntity);
        this.jump = jump;
    }

    @Override
    public void entityAction() {
        super.getLivingEntity().setVelocity(new Vector(0, jump, 0));
    }
}
