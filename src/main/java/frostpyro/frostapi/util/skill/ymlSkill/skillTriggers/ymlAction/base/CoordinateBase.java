package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.base;

import org.bukkit.Location;

public class CoordinateBase {
    private Location location;
    public CoordinateBase(){

    }
    public CoordinateBase(Location location){
        this.location = location;
    }
    public Location getCordinate(){
        return location;
    }

    public void setCordinate(Location location){
        this.location = location;
    }
}
