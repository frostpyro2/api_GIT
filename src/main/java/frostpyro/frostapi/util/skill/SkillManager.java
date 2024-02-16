package frostpyro.frostapi.util.skill;

import frostpyro.frostapi.util.skill.trigger.TriggerType;

public abstract class SkillManager{
    private TriggerType type;

    public SkillManager(TriggerType type){
        this.type = type;
    }


}
