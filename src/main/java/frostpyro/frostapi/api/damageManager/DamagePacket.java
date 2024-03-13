package frostpyro.frostapi.api.damageManager;

import org.bukkit.entity.LivingEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DamagePacket implements Cloneable{
    private LivingEntity target;
    private LivingEntity damagee;
    private DamageType[] type;
    public DamagePacket(LivingEntity target){
        this.target = target;
    }

    public DamagePacket(LivingEntity target, LivingEntity damagee, DamageType...type) {
        this.target = target;
        this.damagee = damagee;
        this.type = type;
    }

    public void setType(DamageType...type){
        this.type = type;
    }

    public DamageType[] getTypeArray() {
        return type;
    }

    public List<DamageType> getTypeList(){
        return new ArrayList<>(Arrays.asList(type));
    }

    public LivingEntity getTarget(){
        return target;
    }

    public LivingEntity getDamagee(){
        return damagee;
    }

    @Override
    public DamagePacket clone() throws CloneNotSupportedException {
        return (DamagePacket) super.clone();
    }
}
