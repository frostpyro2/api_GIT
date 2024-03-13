package frostpyro.frostapi.api.damageManager.damageData;

import java.util.ArrayList;
import java.util.List;

public class DamageData {
    private List<DamagePacket> packets = new ArrayList<>();


    private DamagePacket init;

    public DamageData(){
        init = null;
    }

    public DamageData(double damage, DamageType...types){
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
}
