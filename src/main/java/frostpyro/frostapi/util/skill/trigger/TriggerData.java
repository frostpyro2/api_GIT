package frostpyro.frostapi.util.skill.trigger;

import frostpyro.frostapi.api.damageManager.attackData.AttackData;
import frostpyro.frostapi.api.listeners.customEvents.attackEvents.AttackEvent;
import frostpyro.frostapi.dataManage.stat.StatProvider;
import org.bukkit.Location;

public interface TriggerData {
    StatProvider getCast();
    AttackEvent getEvent();
    AttackData getData();
    Location getSource();
    Location getTargetLoc();
    TriggerType getType();
}
