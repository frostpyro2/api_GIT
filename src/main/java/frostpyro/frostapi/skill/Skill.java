package frostpyro.frostapi.skill;

import frostpyro.frostapi.FrostAPI;
import frostpyro.frostapi.skill.handler.SkillManager;
import frostpyro.frostapi.players.PlayerData;
import frostpyro.frostapi.skill.handler.trigger.TriggerType;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class Skill extends SkillManager {
    private Map<PlayerData, Long> coolDown = new HashMap<>();
    private Set<PlayerData> coolDownSet = new HashSet<>();
    private PlayerData playerData;

    public Skill() {
        super();
    }

    public Skill(ConfigurationSection configuration, PlayerData playerData, Set<Entity> entitySet, TriggerType...type) {
        super(configuration,playerData, entitySet, type);
        this.playerData = playerData;
    }



    @Override
    public void skillActivate() {
        if(!coolDownSet.contains(playerData)){
            super.activateSkill();
            coolDownSet.add(playerData);
        }
        new BukkitRunnable(){
            @Override
            public void run() {
                coolDownSet.remove(playerData);
            }
        }.runTaskLater(FrostAPI.getPlugin(), super.COOL_DOWN() * 20L);

    }
}
