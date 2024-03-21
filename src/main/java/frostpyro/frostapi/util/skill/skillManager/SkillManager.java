package frostpyro.frostapi.util.skill.skillManager;

import frostpyro.frostapi.util.skill.trigger.TriggerData;
import frostpyro.frostapi.util.skill.trigger.TriggerType;

public abstract class SkillManager{
    private TriggerData data;

    public SkillManager(TriggerData data){
        this.data = data;
    }

    public abstract void cast();

    public TriggerData data(){
        return data;
    }
}
