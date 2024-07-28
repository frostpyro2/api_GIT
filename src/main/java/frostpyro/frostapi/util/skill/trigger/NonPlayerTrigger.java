package frostpyro.frostapi.util.skill.trigger;

import frostpyro.frostapi.api.damageManager.attackData.AttackData;
import frostpyro.frostapi.api.listeners.customEvents.attackEvents.AttackEvent;
import frostpyro.frostapi.dataManage.stat.nonPlayer.EntityData;
import frostpyro.frostapi.dataManage.stat.nonPlayer.EntityDataTmp;
import frostpyro.frostapi.dataManage.stat.nonPlayer.EquipSlot;
import org.bukkit.Location;

public class NonPlayerTrigger implements TriggerData{
    private EntityData data;
    private TriggerType type;
    private AttackEvent event;
    private AttackData atkData;
    private Location source;
    private Location targetLocation;
    private EntityDataTmp tmp;
    public NonPlayerTrigger(AttackEvent event, TriggerType type){
        this(event, type, (EntityData) event.getAttack().getAttacker(), event.getAttack(), event.getEntity().getLocation(), event.getAttack().getTarget().getLocation(), ((EntityData) event.getAttack().getAttacker()).getTmp());
    }

    public NonPlayerTrigger(AttackEvent event, TriggerType type, EntityData data, AttackData atkData, Location source, Location targetLocation, EntityDataTmp tmp){
        this.data = data;
        this.atkData = atkData;
        this.type = type;
        this.event = event;
        this.source = source;
        this.targetLocation = targetLocation;
        this.tmp = tmp;
    }



    @Override
    public EntityData getCast() {
        if(data == null) return new EntityData(tmp, EquipSlot.MAIN);
        return data;
    }

    @Override
    public AttackEvent getEvent() {
        return event;
    }

    @Override
    public AttackData getData() {
        return atkData;
    }

    @Override
    public Location getSource() {
        return source;
    }

    @Override
    public Location getTargetLoc() {
        return targetLocation;
    }

    @Override
    public TriggerType getType() {
        return type;
    }
}
