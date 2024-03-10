package frostpyro.frostapi.dataManage.stat;

import frostpyro.frostapi.util.skill.trigger.TriggerType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;

import java.util.UUID;

public class StatProvider {
    private UUID uuid;
    private String name;
    private int skillID;
    private double exp;
    private int level;

    public StatProvider(UUID uuid, String name, int skillID, double exp, int level){
        this.uuid = uuid;
        this.name = name;
        this.skillID = skillID;
        this.exp = exp;
        this.level = level;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSkillID() {
        return skillID;
    }

    public void setSkillID(int skillID) {
        this.skillID = skillID;
    }

    public double getExp() {
        return exp;
    }

    public void setExp(double exp) {
        this.exp = exp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Entity getEntity(){
        return Bukkit.getEntity(uuid);
    }

    public void castSkill(TriggerType type){
        if(skillID == 0) return;
    }
}
