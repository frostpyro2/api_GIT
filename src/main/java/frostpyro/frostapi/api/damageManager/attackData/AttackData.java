package frostpyro.frostapi.api.damageManager.attackData;

import frostpyro.frostapi.dataManage.stat.StatProvider;
import frostpyro.frostapi.dataManage.stat.player.PlayerData;
import org.bukkit.entity.LivingEntity;

public class AttackData {
    private LivingEntity target;
    private StatProvider damagee;
    public AttackData(LivingEntity damage, StatProvider damagee){
        this.target = damage;
        this.damagee = damagee;
    }

    public StatProvider getAttacker(){
        return damagee;
    }

    public LivingEntity getTarget(){
        return target;
    }

    public boolean hasAttacker(){
        return damagee == null;
    }
}
