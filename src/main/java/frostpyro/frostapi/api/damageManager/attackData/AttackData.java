package frostpyro.frostapi.api.damageManager.attackData;

import frostpyro.frostapi.api.damageManager.damageData.DamageData;
import frostpyro.frostapi.dataManage.stat.StatProvider;
import frostpyro.frostapi.dataManage.stat.player.PlayerData;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class AttackData {
    private LivingEntity target;
    private StatProvider damagee;
    private DamageData data;
    public AttackData(DamageData data, LivingEntity damage, StatProvider damagee){
        this.target = damage;
        this.damagee = damagee;
        this.data = data;
    }

    public StatProvider getAttacker(){
        return damagee;
    }

    public DamageData getDamage(){
        return data;
    }

    public LivingEntity getTarget(){
        return target;
    }

    public boolean hasAttacker(){
        return damagee == null;
    }

    public boolean isPlayer(){
        return damagee != null && damagee instanceof PlayerData;
    }
}
