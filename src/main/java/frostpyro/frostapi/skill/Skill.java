package frostpyro.frostapi.skill;

import frostpyro.frostapi.handler.SkillManager;
import frostpyro.frostapi.players.PlayerData;
import frostpyro.frostapi.skill.trigger.TriggerType;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;
import java.util.Map;

public class Skill extends SkillManager {
    private final Map<PlayerData, Long> coolDown = new HashMap<>();
    private PlayerData playerData;

    public Skill() {
        super();
    }

    public Skill(ConfigurationSection configuration, TriggerType type) {
        super(configuration, type);
    }



    @Override
    public void skillActivate() {
        if(coolDown.containsKey(playerData)){
            if(coolDown.get(playerData) > System.currentTimeMillis()) return;
            coolDown.remove(playerData);
        }
        super.activateSkill();
        coolDown.put(playerData, System.currentTimeMillis() + (super.coolDown * 1000L));
    }
}
