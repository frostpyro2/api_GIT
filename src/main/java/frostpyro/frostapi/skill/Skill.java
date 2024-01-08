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
    private PlayerData playerData;

    public Skill() {
        super();
    }
    private ConfigurationSection section;
    public Skill(ConfigurationSection configuration, PlayerData playerData, Set<Entity> entitySet, TriggerType type) {
        super(configuration,playerData, entitySet, type);
        this.playerData = playerData;
        this.section = configuration;
    }



    @Override
    public void skillActivate() {
        super.activateSkill();
    }
}
