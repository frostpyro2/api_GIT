package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CordinateBase {
    protected double x;
    protected double y;
    protected double z;
    public CordinateBase(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public List<Double> getCordinate(){
        return new ArrayList<>(Arrays.asList(x,y,z));
    }

    public void setCordinate(double...cords){
        x = cords[0];
        y = cords[1];
        z = cords[2];
    }
}
