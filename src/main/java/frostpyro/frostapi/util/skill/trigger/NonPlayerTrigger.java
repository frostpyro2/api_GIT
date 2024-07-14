package frostpyro.frostapi.util.skill.trigger;

import frostpyro.frostapi.api.damageManager.attackData.AttackData;
import frostpyro.frostapi.api.listeners.customEvents.attackEvents.AttackEvent;
import frostpyro.frostapi.dataManage.stat.StatProvider;
import frostpyro.frostapi.dataManage.stat.nonPlayer.EntityDataTmp;
import org.bukkit.Location;

public class NonPlayerTrigger implements TriggerData{
    private EntityDataTmp tmp;


    public NonPlayerTrigger(){

    }

    @Override
    public EntityDataTmp getCast() {
        return tmp;
    }

    @Override
    public AttackEvent getEvent() {
        return null;
    }

    @Override
    public AttackData getData() {
        return null;
    }

    @Override
    public Location getSource() {
        return null;
    }

    @Override
    public Location getTargetLoc() {
        return null;
    }

    @Override
    public TriggerType getType() {
        return null;
    }
}
