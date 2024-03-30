package frostpyro.frostapi.util.skill.ymlSkill.yamlInterpret;

import org.bukkit.configuration.ConfigurationSection;

import java.util.List;
import java.util.Map;

public class RadiusInterpret {
    private double x = 0;
    private double y = 0;
    private double z = 0;
    public RadiusInterpret(Map<?, ?> radInfo){
        try{
            x = (double) radInfo.get("x");
            y = (double) radInfo.get("y");
            z = (double) radInfo.get("z");
        }
        catch (Exception any){
            x = 0;
            y = 0;
            z = 0;
        }
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public double getZ(){
        return z;
    }
}
