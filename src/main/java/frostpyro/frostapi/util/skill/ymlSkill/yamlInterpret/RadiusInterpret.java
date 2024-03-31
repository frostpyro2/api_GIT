package frostpyro.frostapi.util.skill.ymlSkill.yamlInterpret;

import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class RadiusInterpret {
    private List<Double> information = new ArrayList<>(Arrays.asList(null, null, null, null, null));
    public RadiusInterpret(Map<?, ?> radInfo){
        try{
            information.set(0, (double)radInfo.get("x"));
            information.set(1, (double)radInfo.get("y"));
            information.set(2, (double)radInfo.get("z"));
            information.set(3, (double)radInfo.get("angle"));
            information.set(4, (double)radInfo.get("distance"));
        }
        catch (Exception any){

        }
    }

    public double getX(){
        if(information.get(0) == null) return 0;
        return information.get(0);
    }

    public double getY(){
        if(information.get(1) == null) return 0;
        return information.get(1);
    }

    public double getZ(){
        if(information.get(2) == null) return 0;
        return information.get(2);
    }

    public double getAngle(){
        if(information.get(3) == null) return 0;
        return information.get(3);
    }

    public double getDistance(){
        if(information.get(4) == null) return 0;
        return information.get(4);
    }
}
