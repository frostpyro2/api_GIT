package frostpyro.frostapi.skill.handler.action_handler.move_handler;

import frostpyro.frostapi.skill.handler.action_handler.TargetAction;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

public class JumpHandler extends TargetAction {
    private double jump;
    public JumpHandler() {
        super();
    }

    public JumpHandler(LivingEntity livingEntity, double jump) {
        super(livingEntity);
        this.jump = jump;
    }

    @Override
    public void entityAction() {
        super.getLivingEntity().setVelocity(new Vector(0, jump, 0));
    }
}
