package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers;

public enum PathName {
    PROJECTILE("skill.projectile"),
    ACTION("skill.action"),
    ARMOR_STAND("skill.armorStand"),
    EFFECT("skill.effect"),
    SOUND("skill.sound")
    ;
    private String actualPath;
    PathName(String actualPath){
        this.actualPath = actualPath;
    }

    public String getActualPath(){
        return actualPath;
    }
}
