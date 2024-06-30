package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.interpret;

import org.bukkit.configuration.ConfigurationSection;

import java.util.List;
import java.util.Map;

public class DelayInterpret {
    int delay = 0;
    Map<?, ?> delayMap;
    public DelayInterpret(Map<?, ?> delayMap){
        this.delayMap = delayMap;
    }

    public int getDelay(){
        return delay;
    }

    public void addDelay(){
        try{
            delay += (int)delayMap.get("addDelay");
        }
        catch (Exception any){
            //do nothing
        }
    }

    public void setDelay(){
        try{
           delay = (int)delayMap.get("setDelay");
        }
        catch (Exception any){
            //do nothing
        }
    }
}
