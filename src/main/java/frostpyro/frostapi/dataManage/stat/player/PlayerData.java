package frostpyro.frostapi.dataManage.stat.player;

import frostpyro.frostapi.dataManage.stat.StatProvider;
import frostpyro.frostapi.util.skill.trigger.TriggerType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;

public class PlayerData extends StatProvider {
    private double money;

    public PlayerData(UUID uuid, String name, int skillID, int level, double exp, double money){
        super(uuid, name, skillID, exp, level);
        this.money = money;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Player getPlayer(){
        return (Player) super.getEntity();
    }

    @Override
    public void castSkill(TriggerType type) {
        super.castSkill(type);
    }
}
