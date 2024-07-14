package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.interpret.components;

import frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.base.ArmorStandDisplay;
import frostpyro.frostapi.util.skill.ymlSkill.skillTriggers.ymlAction.base.Targeting;

public class ArmorStandYml implements ComponentYml{
    private ArmorStandDisplay display;
    private ArmorStandDisplay.ArmorStandInit setting;
    private Targeting targeting;
    public ArmorStandYml(ArmorStandDisplay display){
        this.display = display;
        targeting = new Targeting(display.getStand());
        setting = new ArmorStandDisplay.ArmorStandInit(display.getStand());
    }
    @Override
    public void activation(String path) {

    }

    private void delete(){
        setting.remove();
    }
}
