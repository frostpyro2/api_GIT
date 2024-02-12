package frostpyro.frostapi.util.skill;

import frostpyro.frostapi.players.PlayerData;
import frostpyro.frostapi.skill.Skill;
import frostpyro.frostapi.util.skill.trigger.TriggerType;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.*;

public abstract class SkillManager{
    private TriggerType type;

    public SkillManager(TriggerType type){
        this.type = type;
    }


}
