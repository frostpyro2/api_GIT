package frostpyro.frostapi.players;

import frostpyro.frostapi.skill.trigger.TriggerType;
import org.bukkit.entity.Player;

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

    public void skillCast(TriggerType type){

    }
}
