package frostpyro.frostapi.api.damageManager.damageData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DamageData {
    private List<DamagePacket> packets = new ArrayList<>();

    private DamageType[] types;
    private DamagePacket init;

    public DamageData(){
        init = null;
    }

    public DamageData(double damage, DamageType...types){
        this.types = types;
        init = new DamagePacket(damage, types);
        packets.add(init);
    }

    private final double MIN = .01;

    public double getDamage(){
        double tmp = 0;

        for(DamagePacket packet : packets){
            tmp += packet.getDamage();
        }

        return Math.max(MIN, tmp);
    }

    public double getDamage(double damage){
        return Math.max(MIN, damage);
    }

    public void setDamageType(DamageType...types){
        this.types = types;
    }

    public List<DamageType> getDamageType(){
        return new ArrayList<>(Arrays.asList(types));
    }
}
