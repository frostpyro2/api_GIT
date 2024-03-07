package frostpyro.frostapi.api.damageManager;

import org.bukkit.entity.LivingEntity;

public class DamagePacket {
    private LivingEntity target;
    private LivingEntity damagee;
    private DamageType type;
    public DamagePacket(LivingEntity target, DamageType type){
        this.target = target;
        this.type = type;
    }

    public DamagePacket(LivingEntity target, LivingEntity damagee, DamageType type) {
        this.target = target;
        this.damagee = damagee;
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
}
