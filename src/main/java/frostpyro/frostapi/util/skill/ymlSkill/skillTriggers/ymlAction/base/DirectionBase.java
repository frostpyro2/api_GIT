package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.base;

import java.util.List;

public class DirectionBase extends CordinateBase{
    public DirectionBase(double x, double y, double z) {
        super(x, y, z);
    }

    @Override
    public List<Double> getCordinate() {
        return super.getCordinate();
    }

    @Override
    public void setCordinate(double... cords) {
        super.setCordinate(cords);
    }
}
