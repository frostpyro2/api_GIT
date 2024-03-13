package frostpyro.frostapi.api.damageManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SkillDamage {
    private DamagePacket packet;
    private double amount;

    public SkillDamage(DamagePacket packet, double amount){
        this.packet = packet;
        this.amount = amount;
    }

    public void damage(){
        if(packet.getDamagee() == null){
            packet.getTarget().damage(amount);
            return;
        }
        packet.getTarget().damage(amount, packet.getDamagee());
    }
}
