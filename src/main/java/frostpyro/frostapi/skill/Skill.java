package frostpyro.frostapi.skill;

import frostpyro.frostapi.handler.SkillManager;
import frostpyro.frostapi.players.PlayerData;
import frostpyro.frostapi.handler.trigger.TriggerType;
import org.bukkit.configuration.ConfigurationSection;

public class Skill extends SkillManager {
    private PlayerData playerData;

    public Skill() {
        super();
    }
    private ConfigurationSection section;
    public Skill(ConfigurationSection configuration, PlayerData playerData, TriggerType type) {
        super(configuration,playerData, type);
        this.playerData = playerData;
        this.section = configuration;
    }



    @Override
    public void skillActivate() {
        super.activateSkill();
    }
}
