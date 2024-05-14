package frostpyro.frostapi.util.skill.ymlSkill.skillTriggers;

public enum PathName {
    PROJECTILE("skill.projectile"),
    ACTION("skill.action"),
    ARMOR_STAND("skill.armorStand"),
    EFFECT("skill.effect"),
    SOUND("skill.sound"),
    ACTION_SETTING("action"),
    ARMOR_STAND_SETTING("armorStand"),
    EFFECT_SETTING("effect"),
    SOUND_SETTING("sound"),
    PROJECTILE_SETTING("projectile")
    ;
    private String actualPath;
    PathName(String actualPath){
        this.actualPath = actualPath;
    }

    public String getActualPath(){
        return actualPath;
    }
}
