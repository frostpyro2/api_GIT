package frostpyro.frostapi.dataManage.player;

import frostpyro.frostapi.util.skill.trigger.TriggerType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;

public class PlayerData {
    private String uuid;
    private String name;
    private int skillID;
    private int level;
    private double exp;
    private double money;

    public PlayerData(String uuid, String name, int skillID, int level, double exp, double money){
        this.uuid = uuid;
        this.name = name;
        this.skillID = skillID;
        this.level = level;
        this.exp = exp;
        this.money = money;
    }

    public void setSkillID(int id){
        this.skillID = id;
    }

    public int getSkillID(){
        return this.skillID;
    }

    public Player getPlayer(){
        return Bukkit.getPlayer(UUID.fromString(this.uuid));
    }

    public String getUuid(){
        return uuid;
    }
    public double getExp(){
        return exp;
    }
    public void setExp(double exp){
        this.exp = exp;
    }

    public void castSkill(TriggerType trigger){

    }
}
