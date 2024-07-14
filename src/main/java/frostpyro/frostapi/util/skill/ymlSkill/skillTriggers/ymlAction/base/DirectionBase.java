package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.base;

import org.bukkit.Location;
import org.bukkit.util.Vector;

import java.util.List;

public class DirectionBase extends CoordinateBase{
    private Vector vector;
    public DirectionBase(Location location) {
        super(location);
        vector = super.getCordinate().getDirection();
    }

    public Vector getDirection(){
        return vector;
    }

    @Override
    public Location getCordinate() {
        return super.getCordinate();
    }

    @Override
    public void setCordinate(Location location) {
        super.setCordinate(location);
    }

    public void addLocation(double w){
        super.getCordinate().add(vector.normalize().multiply(w));
    }

    public void addLocation(Vector vector, double w){
        super.getCordinate().add(vector.normalize().multiply(vector).multiply(w));
    }
}
