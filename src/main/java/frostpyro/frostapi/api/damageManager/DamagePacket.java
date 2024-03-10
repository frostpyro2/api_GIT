package frostpyro.frostapi.api.damageManager;

import org.bukkit.entity.LivingEntity;

public class DamagePacket implements Cloneable{
    private LivingEntity target;
    private LivingEntity damagee;
    private DamageType type;
    public DamagePacket(LivingEntity target){
        this.target = target;
    }

    public DamagePacket(LivingEntity target, LivingEntity damagee, DamageType type) {
        this.target = target;
        this.damagee = damagee;
        this.type = type;
    }

    public void setType(DamageType type){
        this.type = type;
    }

    public DamageType getType() {
        return type;
    }

    public LivingEntity getTarget(){
        return target;
    }

    public LivingEntity getDamagee(){
        return damagee;
    }

    @Override
    public DamagePacket clone(){
        DamagePacket clone = new DamagePacket(target, damagee, type);
        return clone;
    }
}
