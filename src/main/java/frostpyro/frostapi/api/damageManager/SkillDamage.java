package frostpyro.frostapi.api.damageManager;

public class SkillDamage {
    private DamagePacket packet;
    private double amount;

    public SkillDamage(DamagePacket packet, double amount){
        this.packet = packet;
        this.amount = amount;
    }

    public void damage(){
        if(packet.getType().equals(DamageType.DEFAULT)||packet.getType().equals(DamageType.PROJECTILE))
            return;
        if(packet.getDamagee() == null){
            packet.getTarget().damage(amount);
            return;
        }
        packet.getTarget().damage(amount, packet.getDamagee());
    }
}
