package frostpyro.frostapi.util.skill.ymlSkill.yamlInterpret;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SettingInterpret {
    private List<Object> settings = new ArrayList<>(Arrays.asList(null, null, null, null));

    public SettingInterpret(Map<?, ?> setting){
        try{
            settings.set(0, setting.get("invisible"));
            settings.set(1, setting.get("gravity"));
            settings.set(2, setting.get("velocity"));
            settings.set(3, setting.get("angle"));
        }
        catch (Exception any){
            //do nothing
        }
    }

    public boolean isInvisible(){
        if(settings.get(0) == null || !(settings.get(0) instanceof Boolean)) return false;
        return (boolean) settings.get(0);
    }

    public boolean isGravity(){
        if(settings.get(1) == null || !(settings.get(1) instanceof Boolean)) return false;
        return (boolean) settings.get(1);
    }

    public double getVelocity(){
        if(settings.get(2) == null || !(settings.get(2) instanceof Double)) return 0;
        return (double) settings.get(2);
    }

    public double getAngle(){
        if(settings.get(3) == null || !(settings.get(3) instanceof Double)) return 0;
        return (double) settings.get(3);
    }
}
