package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers;

import frostpyro.frostapi.util.skill.trigger.TriggerData;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Activation {
    private List<Action> action = new ArrayList<>(Arrays.asList(null, null, null, null));
    public Activation(FileConfiguration configuration, TriggerData data){
        action.set(0, new SkillAction(configuration, data));
        action.set(1, new SkillArmorStand(configuration, data));
        action.set(2, new SkillEffect(configuration, data));
        action.set(3, new SkillSound(configuration, data));
    }

    public List<Action> actions(){
        return action;
    }
}
