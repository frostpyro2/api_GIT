package frostpyro.frostapi.api.damageManager;

import frostpyro.frostapi.api.damageManager.attackData.AttackData;
import frostpyro.frostapi.api.damageManager.damageData.DamageType;
import frostpyro.frostapi.dataManage.stat.StatProvider;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.w3c.dom.CDATASection;

public class Damage {

    private LivingEntity entity;
    private StatProvider attacker;
    private double damage;
    private DamageType[] types;

    public Damage(LivingEntity entity, StatProvider attacker, double damage, DamageType...types){
        this.entity = entity;
        this.attacker = attacker;
        this.damage = damage;
        this.types = types;
    }

    public void setDamage(double damage){
        this.damage = damage;
    }

    public double getDamage(){
        return damage;
    }

    public void setTypes(DamageType...types){
        this.types = types;
    }

    public DamageType[] getTypes(){
        return types;
    }

    public void damage(){
        entity.damage(damage, attacker.getEntity());

    }
}
