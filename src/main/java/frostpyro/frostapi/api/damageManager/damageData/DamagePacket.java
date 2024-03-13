package frostpyro.frostapi.api.damageManager.damageData;

import org.bukkit.entity.LivingEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DamagePacket implements Cloneable{
    private DamageType[] type;
    private double damage;

    public DamagePacket(double damage, DamageType...type) {
        this.damage = damage;
        this.type = type;
    }

    public void setType(DamageType...type){
        this.type = type;
    }

    public double getDamage(){
        return damage;
    }

    public DamageType[] getTypeArray() {
        return type;
    }

    public List<DamageType> getTypeList(){
        return new ArrayList<>(Arrays.asList(type));
    }

    @Override
    public DamagePacket clone() throws CloneNotSupportedException {
        return (DamagePacket) super.clone();
    }
}
