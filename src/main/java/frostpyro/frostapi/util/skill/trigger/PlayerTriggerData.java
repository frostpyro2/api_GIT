package frostpyro.frostapi.util.skill.trigger;

import frostpyro.frostapi.api.damageManager.attackData.AttackData;
import frostpyro.frostapi.api.listeners.customEvents.attackEvents.player.PlayerAttackEvent;
import frostpyro.frostapi.dataManage.stat.player.EquipSlot;
import frostpyro.frostapi.dataManage.stat.player.PlayerData;
import frostpyro.frostapi.dataManage.stat.player.PlayerDataTmp;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

import java.util.HashSet;
import java.util.Set;

public class PlayerTriggerData implements TriggerData{
    private final PlayerDataTmp tmp;
    private final TriggerType type;
    private final EquipSlot slot;
    private final Location source;
    private final Location targetLoc;
    private final AttackData data;
    private final PlayerData cast;
    private PlayerAttackEvent event;
    public PlayerTriggerData(PlayerAttackEvent attackEvent, TriggerType type){
        this(attackEvent.getPlayerData(), type, attackEvent.getAttack().getTarget(), attackEvent.getAttack());
        this.event = attackEvent;
    }

    public PlayerTriggerData(PlayerData data, TriggerType type, LivingEntity target, AttackData atkData){
        this(data.tmp(), type, EquipSlot.MAIN_HAND, data.getEntity().getLocation(), target.getLocation(), target, atkData, data);
    }

    public PlayerTriggerData(PlayerDataTmp playerData, TriggerType type, EquipSlot slot, Location source, Location targetLoc, LivingEntity target, AttackData data, PlayerData cast){
        this.tmp = playerData;
        this.type = type;
        this.slot = slot;
        this.source = source;
        this.targetLoc = targetLoc;
        this.data = data;
        this.cast = cast;
    }


    public PlayerDataTmp getTmp() {
        return tmp;
    }

    public TriggerType getType() {
        return type;
    }

    public EquipSlot getSlot() {
        return slot;
    }

    public Location getSource() {
        return source;
    }

    public PlayerAttackEvent getEvent(){
        return event;
    }

    public Location getTargetLoc() {
        return targetLoc;
    }

    public AttackData getData() {
        return data;
    }

    public PlayerData getCast() {
        if(cast != null) return cast;
        return new PlayerData(tmp, EquipSlot.MAIN_HAND);
    }
}
