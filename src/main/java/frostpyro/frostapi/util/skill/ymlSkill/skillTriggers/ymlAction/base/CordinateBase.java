package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.base;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CordinateBase {
    private Location location;
    public CordinateBase(){

    }
    public CordinateBase(Location location){
        this.location = location;
    }
    public Location getCordinate(){
        return location;
    }

    public void setCordinate(Location location){
        this.location = location;
    }
}
