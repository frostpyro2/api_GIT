package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction;

import org.bukkit.Location;

public class Damaging {
    private double damage;

    private Location damageLoc;

    private double[] distance;

    public Damaging(double damage, Location damageLoc, double[] distance){
        this.damage = damage;
        this.damageLoc = damageLoc;
        this.distance = distance;
    }
}
